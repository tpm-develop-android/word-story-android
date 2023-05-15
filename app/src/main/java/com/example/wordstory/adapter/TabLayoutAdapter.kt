package com.example.wordstory.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wordstory.view.fragment.IntroFragment
import com.example.wordstory.view.fragment.ListFragment

class TabLayoutAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    private lateinit var bundle: Bundle
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment: Fragment
        if (position == 0) {
            fragment = ListFragment()
            fragment.arguments = bundle
        } else {
            fragment = IntroFragment()
            fragment.arguments = bundle
        }
        return fragment
    }

    fun setData(bundle: Bundle) {
        this.bundle = bundle
    }
}