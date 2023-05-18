package com.example.wordstory.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordstory.R
import com.example.wordstory.adapter.FavoriteRecyclerviewAdapter
import com.example.wordstory.adapter.HomeRecyclerviewAdapter
import com.example.wordstory.database.StoriesEntity
import com.example.wordstory.databinding.ActivityFollowBinding
import com.example.wordstory.model.StoriesModel
import com.example.wordstory.viewmodel.DetailViewModel
import com.example.wordstory.viewmodel.MainViewModel

class FollowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFollowBinding
    private lateinit var detailViewModel: DetailViewModel
    private var list: MutableList<StoriesEntity> = mutableListOf()
    private lateinit var favoriteRecyclerviewAdapter: FavoriteRecyclerviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFollowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigation.selectedItemId = R.id.bottom_favorite
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bottom_home -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    true
                }
                R.id.bottom_favorite -> {
                    true
                }
                R.id.bottom_filter -> {
                    startActivity(Intent(applicationContext, FilterActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    true
                }
                R.id.bottom_profile -> {
                    startActivity(Intent(applicationContext, ProfileActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    true
                }
            }
            false
        }
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        list = detailViewModel.getAllFavoriteStories()
        favoriteRecyclerviewAdapter = FavoriteRecyclerviewAdapter(list)
        binding.apply {
            rcvList.layoutManager = LinearLayoutManager(this@FollowActivity)
            rcvList.adapter = favoriteRecyclerviewAdapter
        }

        favoriteRecyclerviewAdapter.onItemClick = {
            val intent = Intent(this, DetailFavouriteActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("dungback", it)
            intent.putExtras(bundle)
            startActivity(intent)
        }

    }

}