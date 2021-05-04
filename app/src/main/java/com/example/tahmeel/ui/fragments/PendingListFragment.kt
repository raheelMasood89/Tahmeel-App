package com.example.tahmeel.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tahmeel.R
import com.example.tahmeel.databinding.FragmentPendingListBinding
import com.example.tahmeel.ui.adapters.PendingListAdapter
import com.example.tahmeel.ui.adapters.PendingLoadStateAdapter
import com.example.tahmeel.ui.viewmodel.PendingListSharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PendingListFragment : Fragment() {
    private lateinit var adapter: PendingListAdapter
    private val viewModel: PendingListSharedViewModel by activityViewModels()
    private lateinit var binding: FragmentPendingListBinding
    private var isViewCreated: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!isViewCreated) {
            isViewCreated = true
            binding = DataBindingUtil.inflate<FragmentPendingListBinding>(
                inflater,
                R.layout.fragment_pending_list,
                container,
                false
            )
            initViews(binding)
        }
        return binding.root
    }

    private fun initViews(binding: FragmentPendingListBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.searchIcon.setOnClickListener {
            view?.findNavController()
                ?.navigate(R.id.action_pendingListFragment_to_searchListFragment)
        }
        binding.viewModel = viewModel
        binding.rv.layoutManager = GridLayoutManager(activity, 1)
        initAdapter()
        observeList()
    }

    private fun observeList() {
        lifecycleScope.launch {
            viewModel.getPendingListData.collectLatest { pagedData ->
                adapter.submitData(pagedData)
            }
        }
    }

    private fun initAdapter() {
        adapter = PendingListAdapter(PendingListAdapter.PendingListClickListener { data ->
        })
        binding.rv.adapter = adapter.withLoadStateFooter(
            footer = PendingLoadStateAdapter { adapter.retry() }
        )
    }

}