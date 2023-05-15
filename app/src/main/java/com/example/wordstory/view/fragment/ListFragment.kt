package com.example.wordstory.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordstory.adapter.DetailRecyclerviewAdapter
import com.example.wordstory.databinding.FragmentListBinding
import com.example.wordstory.model.ChaptersModel
import com.example.wordstory.model.StoriesModel
import com.example.wordstory.view.activity.ChapterActivity
import com.example.wordstory.viewmodel.ListFragmentViewModel


class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: DetailRecyclerviewAdapter
    private lateinit var viewModel: ListFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val storiesModel = arguments?.getSerializable("dung") as StoriesModel?
        viewModel = ViewModelProvider(this)[ListFragmentViewModel::class.java]
        if (storiesModel != null) {
            viewModel.setData(storiesModel)
        }
        val data: MutableList<ChaptersModel> = viewModel.getFragmentRecyclerViewData().reversed().toMutableList()
        adapter = DetailRecyclerviewAdapter(data)
        binding.apply {
            rcvStory.layoutManager = LinearLayoutManager(requireContext())
            rcvStory.adapter = adapter
        }
        adapter.onItemClick = {
            val intent = Intent(activity, ChapterActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("dungbach", it)
            bundle.putSerializable("bachdung", storiesModel)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

}