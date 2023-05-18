package com.example.wordstory.view.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.InputType
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.wordstory.R
import com.example.wordstory.adapter.HomeRecyclerviewAdapter
import com.example.wordstory.adapter.SliderAdapter
import com.example.wordstory.adapter.ViewPagerAdapter
import com.example.wordstory.databinding.ActivityMainBinding
import com.example.wordstory.model.StoriesModel
import com.example.wordstory.viewmodel.MainViewModel
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigation.selectedItemId = R.id.bottom_home
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_home -> {
                    binding.viewPager.currentItem = 0
                }
                R.id.bottom_favorite -> {
                    binding.viewPager.currentItem = 1
                }
                R.id.bottom_filter -> {
                    binding.viewPager.currentItem = 2
                }
                R.id.bottom_profile -> {
                    binding.viewPager.currentItem = 3
                }
            }
            true
        }

        setUpViewPager()
    }

    private fun setUpViewPager(){
       var viewPagerAdapter : ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        binding.viewPager.adapter = viewPagerAdapter
    }
}