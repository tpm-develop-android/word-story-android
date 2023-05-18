package com.example.wordstory.view.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.wordstory.R
import com.example.wordstory.adapter.HomeRecyclerviewAdapter
import com.example.wordstory.adapter.SliderAdapter
import com.example.wordstory.databinding.FragmentHomeBinding
import com.example.wordstory.model.StoriesModel
import com.example.wordstory.view.activity.DetailActivity
import com.example.wordstory.viewmodel.MainViewModel
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private lateinit var mainViewModel: MainViewModel
    private var compositePageTransformer: CompositePageTransformer = CompositePageTransformer()
    private var sliderHandler: Handler = Handler()
    private var listStoriesModel: MutableList<StoriesModel> = mutableListOf()
    private lateinit var homeRecyclerViewAdapter: HomeRecyclerviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
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
            page.scaleY = r + 0.85f

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
        print(listStoriesModel.size)
        homeRecyclerViewAdapter = HomeRecyclerviewAdapter()
        binding.apply {
            rcvList.layoutManager = LinearLayoutManager(context)
            rcvList.adapter = homeRecyclerViewAdapter
            homeRecyclerViewAdapter.setFilteredList(listStoriesModel)
        }
        homeRecyclerViewAdapter.onItemClick = {
            val intent = Intent(activity, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("dungback", it)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun filterList(query: String?) {
        Log.d("dungback", "onQueryTextChange: ${Thread.currentThread().name}")
        if (query != null) {
            val filteredList: MutableList<StoriesModel> = mutableListOf()
            filteredList.clear()

            Log.d("dungback", "filterList: ${filteredList.size}")
            for (i in listStoriesModel) {
                if (i.name?.lowercase(Locale.ROOT)?.contains(query) == true) {
                    filteredList.add(i)
                }

            }
            Log.d("dungback", "filterList: ${listStoriesModel.size}")
            activity?.runOnUiThread {if (filteredList.isEmpty()) {
                Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show()
            } else {
                homeRecyclerViewAdapter.setFilteredList(filteredList)
            }
            }
        }
    }
}