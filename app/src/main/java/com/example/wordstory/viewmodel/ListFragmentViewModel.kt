package com.example.wordstory.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.wordstory.model.ChaptersModel
import com.example.wordstory.model.StoriesModel
import com.example.wordstory.repository.MainRepository

class ListFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MainRepository = MainRepository(application)
    private var chaptersModel: MutableList<ChaptersModel> = mutableListOf()
    private lateinit var model: StoriesModel

    fun getFragmentRecyclerViewData(): MutableList<ChaptersModel> {
        val nameChapter = repository.getNameChapter(model.id)
        val idChapter = repository.getIdChapter(model.id)
        val bookIdChapter = repository.getIdBook(model.id)
        val linkChapter = repository.getLinkChapter(model.id)
        val contentHTMLChapter = repository.getHTMLChapter(model.id)
        val contentStringChapter = repository.getStringChapter(model.id)
        val arraySize = nameChapter!!.size - 1
        for (i in 0..arraySize) {
            chaptersModel.add(
                ChaptersModel(
                    idChapter!![i],
                    model.id,
                    bookIdChapter!![i],
                    nameChapter!![i],
                    linkChapter!![i],
                    contentHTMLChapter!![i],
                    contentStringChapter!![i]
                )
            )
        }
        return chaptersModel
    }

    fun setData(model: StoriesModel) {
        this.model = model
    }
}