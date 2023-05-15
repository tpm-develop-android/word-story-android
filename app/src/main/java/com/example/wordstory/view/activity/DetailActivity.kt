package com.example.wordstory.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.wordstory.R
import com.example.wordstory.adapter.TabLayoutAdapter
import com.example.wordstory.databinding.ActivityDetailBinding
import com.example.wordstory.model.StoriesModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var adapter: TabLayoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        //init
        initToolbar()
        binding.collapsee.setContentInsetsRelative(0, 0)
        val customToolbar = layoutInflater.inflate(R.layout.custom_toolbar, null)
        val customTitle = customToolbar.findViewById<TextView>(R.id.custom_title)
        val customImage = customToolbar.findViewById<ImageView>(R.id.custom_image)

        //receive model
        val storiesModel = intent.extras?.get("dungback") as StoriesModel

        //custom toolbar and animation
        binding.collapsee.addView(customToolbar)
        customTitle.text = storiesModel.name
        Glide.with(applicationContext).load(storiesModel.image).into(customImage)
        binding.appbarCollapse.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset == 0) {
                binding.collapsee.visibility = View.GONE
            } else if (Math.abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                // Fully collapsed
                binding.collapsee.visibility = View.VISIBLE
            } else {
                // Partially collapsed
                binding.collapsee.visibility = View.GONE
            }
        })

        //pass data to fragments
        val bundle = Bundle()
        bundle.putSerializable("dung", storiesModel)
        adapter = TabLayoutAdapter(supportFragmentManager, lifecycle)
        adapter.setData(bundle)

        //setView
        binding.tvName.text = storiesModel.name
        Glide.with(applicationContext).load(storiesModel.image).into(binding.shapeimageView)
        if (storiesModel.author == null || storiesModel.author == "") {
            binding.tvNameAuthor.text = "Đang cập nhật"
        } else {
            binding.tvNameAuthor.text = storiesModel.author
        }
        binding.buttonBack.setOnClickListener {
            val buttonClick = AlphaAnimation(1F, 0.8F)
            it.startAnimation(buttonClick)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val buttonClick = AlphaAnimation(1F, 0.8F)

        binding.tablayout.addTab(binding.tablayout.newTab().setText("Danh sách chương"))
        binding.tablayout.addTab(binding.tablayout.newTab().setText("Giới thiệu"))

        binding.viewpager.adapter = adapter
        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewpager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tablayout.selectTab(binding.tablayout.getTabAt(position))
            }
        })

    }

    private fun initToolbar() {
        binding.appBar.requestLayout()
        setSupportActionBar(binding.collapsee)
    }
}
