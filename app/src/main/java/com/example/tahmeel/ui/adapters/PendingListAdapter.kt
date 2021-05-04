package com.example.tahmeel.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tahmeel.databinding.ItemPendingListBinding
import com.example.tahmeel.model.data.PendingOrder

class PendingListAdapter(private val clickListener: PendingListClickListener) :
    PagingDataAdapter<PendingOrder, PendingListAdapter.PendingListViewHolder>(Companion) {

    inner class PendingListViewHolder(val binding: ItemPendingListBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object : DiffUtil.ItemCallback<PendingOrder>() {
        override fun areItemsTheSame(oldItem: PendingOrder, newItem: PendingOrder): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: PendingOrder, newItem: PendingOrder): Boolean =
            oldItem.orderPriceFormatted == newItem.orderPriceFormatted
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPendingListBinding.inflate(layoutInflater, parent, false)
        return PendingListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PendingListViewHolder, position: Int) {
        val currentUser = getItem(position)
        holder.binding.data = currentUser
        holder.binding.clickListener = clickListener
        holder.binding.executePendingBindings()
    }

    class PendingListClickListener(val clickListener: (mData: PendingOrder) -> Unit) {
        fun onClick(mMovieId: PendingOrder) = clickListener(mMovieId)
    }
}