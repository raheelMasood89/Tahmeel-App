package com.example.tahmeel.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tahmeel.databinding.ItemSearchListBinding
import com.example.tahmeel.model.data.PendingOrder

class SearchListAdapter(private val clickListener: SearchClickListener) :
    ListAdapter<PendingOrder, SearchListAdapter.SearchViewHolder>(Companion) {

    inner class SearchViewHolder(val binding: ItemSearchListBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object : DiffUtil.ItemCallback<PendingOrder>() {
        override fun areItemsTheSame(oldItem: PendingOrder, newItem: PendingOrder): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: PendingOrder, newItem: PendingOrder): Boolean =
            oldItem.orderNumber == newItem.orderNumber
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchListBinding.inflate(layoutInflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val currentUser = getItem(position)
        holder.binding.data = currentUser
        holder.binding.clickListener = clickListener
        holder.binding.executePendingBindings()
    }

    class SearchClickListener(val clickListener: (data: PendingOrder) -> Unit) {
        fun onClick(data: PendingOrder) = clickListener(data)
    }
}