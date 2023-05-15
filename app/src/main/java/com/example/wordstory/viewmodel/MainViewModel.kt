package com.example.wordstory.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.wordstory.R
import com.example.wordstory.model.SlideItem
import com.example.wordstory.model.StoriesModel
import com.example.wordstory.repository.MainRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
//    val sliderItems: MutableList<SlideItem> = mutableListOf(
//        SlideItem(R.drawable.img1),
//        SlideItem(R.drawable.img2),
//        SlideItem(R.drawable.img3),
//        SlideItem(R.drawable.img4),
//        SlideItem(R.drawable.img5)
//    )

    private val repository: MainRepository = MainRepository(application)
    private var listStoriesModel: MutableList<StoriesModel> = mutableListOf()

    fun getMainRecyclerViewData(): MutableList<StoriesModel> {
        val nameList = repository.getNames()
        val imageList = repository.getImages()
        val idList = repository.getIds()
        val authorList = repository.getAuthors()
        val summaryList = repository.getSummary()
        val linkList = repository.getLinks()
        val pathList = repository.getPaths()
        val storageList = repository.getStorage()
        val chaptersizeList = repository.getChaptersize()
        val arraySize = nameList.size - 1
        for (i in 0..arraySize) {
            listStoriesModel.add(
                StoriesModel(
                    idList[i],
                    nameList[i],
                    imageList[i],
                    authorList[i],
                    summaryList[i],
                    linkList[i],
                    pathList[i],
                    storageList[i],
                    chaptersizeList[i]
                )
            )
        }
        return listStoriesModel
    }

    fun getListViewpager() : MutableList<SlideItem>{
        var imageList : MutableList<SlideItem> = mutableListOf()
        var list = getMainRecyclerViewData()
        var randomElements = list.shuffled().distinct().take(5)
        for( i in 0..4){
            randomElements[i].image?.let { SlideItem(it) }?.let { imageList.add(it) }
        }
        return imageList

    }

    fun getViewPagerData(): MutableList<SlideItem> {
        return getListViewpager()
    }

    fun set() {}
}