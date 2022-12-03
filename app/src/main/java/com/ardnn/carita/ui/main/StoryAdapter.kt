package com.ardnn.carita.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse
import com.ardnn.carita.databinding.ItemStoryBinding
import com.ardnn.carita.ui.util.loadImage
import com.ardnn.carita.ui.util.withDateFormat

class StoryAdapter(
    private val clickListener: OnItemClickListener
) : PagingDataAdapter<StoryResponse, StoryAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.onBind(data)
        }
    }

    inner class ViewHolder(
        private val binding: ItemStoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(story: StoryResponse) {
            with(binding) {
                ivPhoto.loadImage(story.photoUrl)
                tvName.text = story.name
                tvDate.text = story.createdAt.withDateFormat()
                root.setOnClickListener {
                    clickListener.onItemClick(story)
                }
            }
        }
    }

    fun interface OnItemClickListener {

        fun onItemClick(story: StoryResponse)
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<StoryResponse>() {
            override fun areItemsTheSame(oldItem: StoryResponse, newItem: StoryResponse): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: StoryResponse,
                newItem: StoryResponse
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}