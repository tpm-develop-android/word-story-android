package com.example.wordstory.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordstory.adapter.HomeRecyclerviewAdapter
import com.example.wordstory.databinding.ActivitySearchBinding
import com.example.wordstory.model.StoriesModel
import com.example.wordstory.viewmodel.MainViewModel
import java.util.*

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var mainViewModel: MainViewModel
    private var listStoriesModel: MutableList<StoriesModel> = mutableListOf()
    private var filteredList: MutableList<StoriesModel> = mutableListOf()
    private lateinit var homeRecyclerViewAdapter: HomeRecyclerviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        listStoriesModel = mainViewModel.getMainRecyclerViewData()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText == null){
                    binding.tvEmpty.visibility = View.VISIBLE
                }else{
                    binding.tvEmpty.visibility = View.INVISIBLE
                }

                val searchInput = newText?.toLowerCase()

                for(obj in listStoriesModel){
                    val name = obj.name?.toLowerCase()

                    if (name != null && searchInput != "") {
                        if (searchInput?.let { name.contains(it) } == true){
                            filteredList.add(obj)
                        }

                    }else{
                        binding.rcvList.visibility = View.INVISIBLE
                        binding.tvEmpty.visibility = View.VISIBLE
                    }
                }
                return true
            }

        })
        print(filteredList)
        if(filteredList.size == 0){
            binding.rcvList.visibility = View.INVISIBLE
            binding.tvEmpty.visibility = View.VISIBLE
        }
        homeRecyclerViewAdapter = HomeRecyclerviewAdapter()
        binding.apply {
            rcvList.layoutManager = LinearLayoutManager(this@SearchActivity)
            rcvList.adapter = homeRecyclerViewAdapter
        }

    }
}