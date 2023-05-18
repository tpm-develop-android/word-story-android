package com.example.wordstory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wordstory.database.StoriesEntity
import com.example.wordstory.databinding.ItemRecyclerviewfavoriteBinding
import com.example.wordstory.databinding.ItemRecyclerviewhomeBinding
import com.example.wordstory.model.StoriesModel

class FavoriteRecyclerviewAdapter(private val items: MutableList<StoriesEntity>) :
    RecyclerView.Adapter<FavoriteRecyclerviewAdapter.ViewHolder>() {

    var onItemClick : ((StoriesEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerviewfavoriteBinding.inflate(inflater, parent, false)
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

    inner class ViewHolder(private val binding: ItemRecyclerviewfavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(holder: ViewHolder, item: StoriesEntity) {
            binding.apply {
                tvNameList.text = item.name
                Glide.with(holder.itemView.context).load(item.image).into(shapeimageView)
                tvChapterSize.text = "Chương ${item.chaptersize}"

            }
        }
    }



}