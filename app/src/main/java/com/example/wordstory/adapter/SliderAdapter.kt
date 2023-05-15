package com.example.wordstory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.wordstory.R
import com.example.wordstory.model.SlideItem
import com.google.android.material.imageview.ShapeableImageView

class SliderAdapter(val sliderItems: MutableList<SlideItem>, val viewPager2: ViewPager2) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.slide_item_container, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.setImage(sliderItems[position])

    }

    override fun getItemCount(): Int {
        return sliderItems.size
    }

    inner class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val imageView: ShapeableImageView = itemView.findViewById(R.id.imageSlide)

        fun setImage(sliderItem: SlideItem){
         //   imageView.setImageResource(sliderItem.image)
            Glide.with(itemView.context).load(sliderItem.image).into(imageView)
        }

    }

}