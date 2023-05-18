package com.example.wordstory.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.wordstory.view.fragment.FavoriteFragment
import com.example.wordstory.view.fragment.FilterFragment
import com.example.wordstory.view.fragment.HomeFragment
import com.example.wordstory.view.fragment.ProfileFragment

class ViewPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior ) {
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> {
                return HomeFragment()
            }
            1 -> {
                return FavoriteFragment()
            }
            2 -> {
                return FilterFragment()
            }
            3 -> {
                return ProfileFragment()
            }
            else -> {
                return HomeFragment()
            }
        }
    }
}