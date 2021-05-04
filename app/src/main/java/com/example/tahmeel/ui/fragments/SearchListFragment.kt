package com.example.tahmeel.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tahmeel.R
import com.example.tahmeel.databinding.FragmentSearchBinding
import com.example.tahmeel.model.response.OrdersResponse
import com.example.tahmeel.ui.adapters.SearchListAdapter
import com.example.tahmeel.ui.viewmodel.PendingListSharedViewModel
import com.example.tahmeel.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchListFragment : Fragment() {
    private val viewModel: PendingListSharedViewModel by activityViewModels()
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: SearchListAdapter
    private var isViewCreated: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        if (!isViewCreated) {
            isViewCreated = true
            binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_search,
                container,
                false
            )
            binding.lifecycleOwner = viewLifecycleOwner
            binding.search.queryHint = "Search Tahmeel Fee"
            binding.search.setBackgroundColor(
                ContextCompat.getColor(
                    container?.context!!,
                    R.color.app_bar_color
                )
            )
            binding.search.performClick()
            binding.search.setIconifiedByDefault(false);
            binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d("", "")
                    if (query != null) {
                        viewModel.searchByString(query)
                    }
                    return false;
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })
            adapter = SearchListAdapter(SearchListAdapter.SearchClickListener { data -> })
            binding.rv.adapter = adapter
            binding.rv.layoutManager = GridLayoutManager(activity, 1)
            observeSearchList()
        }
        return binding.root
    }

    private fun observeSearchList() {
        viewModel.searchListData.observe(viewLifecycleOwner, Observer { data ->
            when (data) {
                is DataState.Success<OrdersResponse> -> {
                    data.value?.let {
                        adapter.submitList(it.pendingOrders)
                    }
                    Log.d("", "")
                }
                is DataState.Loading -> {
                    Log.d("", "")
                }
                is DataState.NetworkError -> {
                    Log.d("", "")
                }
                is DataState.GenericError -> {
                    Log.d("", "")
                }
            }
        })
    }
}