package com.example.wordstory.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wordstory.databinding.FragmentIntroBinding
import com.example.wordstory.model.StoriesModel


class IntroFragment : Fragment() {
    private lateinit var binding: FragmentIntroBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIntroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val storiesModel = arguments?.getSerializable("dung") as StoriesModel?
        if (storiesModel != null) {
            binding.description.text = storiesModel.summary
        } else {
            binding.description.text = "Đang cập nhật"
        }
    }

}