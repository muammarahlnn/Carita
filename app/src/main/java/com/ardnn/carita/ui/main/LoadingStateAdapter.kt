package com.ardnn.carita.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ardnn.carita.R
import com.ardnn.carita.databinding.ItemLoadingBinding
import timber.log.Timber

class LoadingStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<LoadingStateAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val binding = ItemLoadingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding, retry)
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.onBind(loadState)
    }

    class ViewHolder(
        private val binding: ItemLoadingBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun onBind(loadState: LoadState) {
            with(binding) {
                if (loadState is LoadState.Error) {
                    tvError.text = loadState.error.localizedMessage
                }
                if (loadState is LoadState.NotLoading) {
                    Timber.d("GOKS")
                    if (loadState.endOfPaginationReached) {
                        Timber.d("GOKS")
                        tvError.text = itemView.context.getString(R.string.error_connection)
                    }
                }
                tvError.isVisible = loadState is LoadState.Error
                progressBar.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState is LoadState.Error
            }
        }
    }
}