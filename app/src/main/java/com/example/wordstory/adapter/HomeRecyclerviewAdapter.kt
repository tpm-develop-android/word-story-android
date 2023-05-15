package com.example.wordstory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wordstory.databinding.ItemRecyclerviewhomeBinding
import com.example.wordstory.model.StoriesModel

class HomeRecyclerviewAdapter(private val items: MutableList<StoriesModel>) :
    RecyclerView.Adapter<HomeRecyclerviewAdapter.ViewHolder>() {

    var onItemClick : ((StoriesModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerviewhomeBinding.inflate(inflater, parent, false)
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

    inner class ViewHolder(private val binding: ItemRecyclerviewhomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(holder: ViewHolder, item: StoriesModel) {
            binding.apply {
                tvNameList.text = item.name
                Glide.with(holder.itemView.context).load(item.image).into(shapeimageView)
                tvChapterSize.text = "Chương ${item.chaptersize}"

            }
        }
    }

}