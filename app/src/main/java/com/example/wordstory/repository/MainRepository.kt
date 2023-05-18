package com.example.wordstory.repository

import android.app.Application
import com.example.wordstory.database.*

class MainRepository(application: Application) {

    private val myDao : MyDao
    private val favoriteDao: FavoriteDao

    init {
        val database = MyDatabase.getInstance(application)
        myDao = database.myDao()
        val favoriteDatabase = FavoriteDatabase.getInstance(application)
        favoriteDao = favoriteDatabase.favoriteDao()

    }

    fun getAllFavouriteStories(): MutableList<StoriesEntity>{
        return favoriteDao.getAllFavouriteStories()
    }

    fun insertStory(storiesEntity: StoriesEntity){
        return favoriteDao.insertStory(storiesEntity)
    }

    fun getStoryById(id: String) : StoriesEntity?{
        return favoriteDao.getStoryById(id)
    }

    fun deleteStory(storiesEntity: StoriesEntity){
        return favoriteDao.deleteStory(storiesEntity)
    }

    fun getNames(): List<String>{
        return myDao.getNames()
    }

    fun getImages(): List<String>{
        return myDao.getImages()
    }

    fun getIds(): List<String>{
        return myDao.getIds()
    }

    fun getAuthors(): List<String>{
        return myDao.getAuthors()
    }

    fun getSummary(): List<String>{
        return myDao.getSummary()
    }

    fun getLinks(): List<String>{
        return myDao.getLinks()
    }

    fun getPaths(): List<String>{
        return myDao.getPaths()
    }

    fun getStorage(): List<String>{
        return myDao.getStorage()
    }

    fun getIdChapter(id: String): List<String>{
        return myDao.getIdChapter(id)
    }

    fun getIdBook(id: String): List<String>{
        return myDao.getIdBook(id)
    }

    fun getNameChapter(id: String): List<String>{
        return myDao.getNameChapter(id)
    }

    fun getLinkChapter(id: String): List<String>{
        return myDao.getLinkChapter(id)
    }

    fun getHTMLChapter(id: String): List<String>{
        return myDao.getHTMLChapter(id)
    }

    fun getStringChapter(id: String): List<String>{
        return myDao.getStringChapter(id)
    }

    fun getChaptersize(): List<String>{
        return myDao.getChaptersize()
    }

}