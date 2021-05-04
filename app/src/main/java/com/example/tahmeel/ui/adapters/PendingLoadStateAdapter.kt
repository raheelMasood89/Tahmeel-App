package com.example.tahmeel.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tahmeel.databinding.LoadingErrorStateBinding
import com.example.tahmeel.util.Utils.visible

class PendingLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PendingLoadStateAdapter.MovieLoadStateViewHolder>() {

    inner class MovieLoadStateViewHolder(
        private val binding: LoadingErrorStateBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.textViewError.text = loadState.error.localizedMessage
            }
            binding.progressbar.visible(loadState is LoadState.Loading)
            binding.buttonRetry.visible(loadState is LoadState.Error)
            binding.textViewError.visible(loadState is LoadState.Error)
            binding.buttonRetry.setOnClickListener {
                retry()
            }
        }
    }

    override fun onBindViewHolder(holder: MovieLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = MovieLoadStateViewHolder(
        LoadingErrorStateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        retry
    )
}