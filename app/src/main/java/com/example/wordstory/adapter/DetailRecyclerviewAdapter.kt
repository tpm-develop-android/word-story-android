package com.example.wordstory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wordstory.databinding.ItemStoryChapterBinding
import com.example.wordstory.model.ChaptersModel
import com.example.wordstory.model.StoriesModel

class DetailRecyclerviewAdapter(private val items: MutableList<ChaptersModel>) : RecyclerView.Adapter<DetailRecyclerviewAdapter.ViewHolder>() {

    var onItemClick : ((ChaptersModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStoryChapterBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(holder, items[position])
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(items[position])
        }
    }

    override fun getItemCount(): Int {
       return items.size
    }

    inner class ViewHolder(private val binding: ItemStoryChapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(holder: ViewHolder, item: ChaptersModel) {
            binding.apply {
                chapterName.text = item.name
            }
        }
    }

}