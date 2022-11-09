package com.ardnn.carita.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse
import com.ardnn.carita.databinding.ItemStoryBinding
import com.ardnn.carita.ui.util.loadImage
import com.ardnn.carita.ui.util.withDateFormat

class StoryAdapter(
    private val list: List<StoryResponse>,
    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

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
}