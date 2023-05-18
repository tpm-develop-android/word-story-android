package com.example.wordstory.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.wordstory.database.StoriesEntity
import com.example.wordstory.model.StoriesModel
import com.example.wordstory.repository.MainRepository

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MainRepository = MainRepository(application)

    fun insertStory(storiesEntity: StoriesEntity){
        return repository.insertStory(storiesEntity)
    }

    fun deleteStory(storiesEntity: StoriesEntity){
        return repository.deleteStory(storiesEntity)

    }
    fun getAllFavoriteStories() : MutableList<StoriesEntity>{
        return repository.getAllFavouriteStories()
    }

    fun getStoryById(id: String) : StoriesEntity?{
        return repository.getStoryById(id)
    }
}