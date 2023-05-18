package com.example.wordstory.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordstory.R
import com.example.wordstory.adapter.FavoriteRecyclerviewAdapter
import com.example.wordstory.database.StoriesEntity
import com.example.wordstory.databinding.FragmentFavoriteBinding
import com.example.wordstory.view.activity.DetailFavouriteActivity
import com.example.wordstory.viewmodel.DetailViewModel


class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var detailViewModel: DetailViewModel
    private var list: MutableList<StoriesEntity> = mutableListOf()
    private lateinit var favoriteRecyclerviewAdapter: FavoriteRecyclerviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        list = detailViewModel.getAllFavoriteStories()
        favoriteRecyclerviewAdapter = FavoriteRecyclerviewAdapter(list)
        binding.apply {
            rcvList.layoutManager = LinearLayoutManager(context)
            rcvList.adapter = favoriteRecyclerviewAdapter
        }

        favoriteRecyclerviewAdapter.onItemClick = {
            val intent = Intent(activity, DetailFavouriteActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("dungback", it)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}