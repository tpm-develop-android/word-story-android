package com.example.wordstory.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.wordstory.R
import com.example.wordstory.adapter.TabLayoutAdapter
import com.example.wordstory.database.FavoriteDatabase
import com.example.wordstory.database.StoriesEntity
import com.example.wordstory.databinding.ActivityDetailBinding
import com.example.wordstory.model.StoriesModel
import com.example.wordstory.viewmodel.DetailViewModel
import com.example.wordstory.viewmodel.MainViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout

class DetailFavouriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var adapter: TabLayoutAdapter
    private lateinit var viewmodel: DetailViewModel

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
        val storiesEntity = intent.extras?.get("dungback") as StoriesEntity

        //custom toolbar and animation
        binding.collapsee.addView(customToolbar)
        customTitle.text = storiesEntity.name
        Glide.with(applicationContext).load(storiesEntity.image).into(customImage)
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
        bundle.putSerializable("dung", convertEntity(storiesEntity))
        adapter = TabLayoutAdapter(supportFragmentManager, lifecycle)
        adapter.setData(bundle)

        //setView
        binding.tvName.text = storiesEntity.name
        Glide.with(applicationContext).load(storiesEntity.image).into(binding.shapeimageView)
        if (storiesEntity.author == null || storiesEntity.author == "") {
            binding.tvNameAuthor.text = "Đang cập nhật"
        } else {
            binding.tvNameAuthor.text = storiesEntity.author
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

        viewmodel = ViewModelProvider(this)[DetailViewModel::class.java]

        val itemId = storiesEntity.id
        val itemExists = viewmodel.getStoryById(itemId) != null
        binding.btnCheckbox.isChecked = itemExists

        binding.btnCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                viewmodel.insertStory(StoriesEntity(storiesEntity.id, storiesEntity.name, storiesEntity.image, storiesEntity.author, storiesEntity.summary, storiesEntity.link, storiesEntity.path, storiesEntity.storage, storiesEntity.chaptersize, null))
                val stories =  viewmodel.getAllFavoriteStories()
                print(stories)
            }else{
                viewmodel.deleteStory(StoriesEntity(storiesEntity.id, storiesEntity.name, storiesEntity.image, storiesEntity.author, storiesEntity.summary, storiesEntity.link, storiesEntity.path, storiesEntity.storage, storiesEntity.chaptersize, null))
            }
        }


    }

    private fun initToolbar() {
        binding.appBar.requestLayout()
        setSupportActionBar(binding.collapsee)
    }

    private fun convertEntity(storiesEntity: StoriesEntity): StoriesModel{
        return StoriesModel(storiesEntity.id, storiesEntity.name, storiesEntity.image, storiesEntity.author, storiesEntity.summary, storiesEntity.link, storiesEntity.path, storiesEntity.storage, storiesEntity.chaptersize)
    }
}
