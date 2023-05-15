package com.example.wordstory.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.wordstory.adapter.HomeRecyclerviewAdapter
import com.example.wordstory.adapter.SliderAdapter
import com.example.wordstory.databinding.ActivityMainBinding
import com.example.wordstory.model.StoriesModel
import com.example.wordstory.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private var compositePageTransformer: CompositePageTransformer = CompositePageTransformer()
    private var sliderHandler: Handler = Handler()
    private var listStoriesModel: MutableList<StoriesModel> = mutableListOf()
    private lateinit var binding: ActivityMainBinding
    private lateinit var homeRecyclerViewAdapter: HomeRecyclerviewAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        initViewPager()
        initRecyclerView()
    }

    private var sliderRunnable = Runnable {
        if (binding.viewPagerImageSlider.currentItem == 4) {
            binding.viewPagerImageSlider.currentItem = 0
        } else {
            binding.viewPagerImageSlider.currentItem = binding.viewPagerImageSlider.currentItem + 1
        }
    }

    private fun initViewPager() {
        binding.viewPagerImageSlider.adapter =
            SliderAdapter(mainViewModel.getViewPagerData(), binding.viewPagerImageSlider)
        binding.viewPagerImageSlider.clipToPadding = false
        binding.viewPagerImageSlider.clipChildren = false
        binding.viewPagerImageSlider.offscreenPageLimit = 3
        binding.viewPagerImageSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        compositePageTransformer.addTransformer(MarginPageTransformer(0))
        val pageTransformer = ViewPager2.PageTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY =  r + 0.85f

        }

        compositePageTransformer.addTransformer(pageTransformer)
        binding.viewPagerImageSlider.setPageTransformer(compositePageTransformer)
        binding.viewPagerImageSlider.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)
            }
        })
    }

    private fun initRecyclerView() {
        listStoriesModel = mainViewModel.getMainRecyclerViewData()
        homeRecyclerViewAdapter = HomeRecyclerviewAdapter(listStoriesModel)
        binding.apply {
            rcvList.layoutManager = LinearLayoutManager(this@MainActivity)
            rcvList.adapter = homeRecyclerViewAdapter
        }
        homeRecyclerViewAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("dungback", it)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}