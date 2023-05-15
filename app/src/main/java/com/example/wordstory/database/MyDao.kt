package com.example.wordstory.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface MyDao {
    @Query("SELECT name FROM stories")
    fun getNames(): List<String>

    @Query("SELECT image FROM stories")
    fun getImages(): List<String>

    @Query("SELECT id FROM stories")
    fun getIds(): List<String>

    @Query("SELECT author FROM stories")
    fun getAuthors(): List<String>

    @Query("SELECT summary FROM stories")
    fun getSummary(): List<String>

    @Query("SELECT link FROM stories")
    fun getLinks(): List<String>

    @Query("SELECT path FROM stories")
    fun getPaths(): List<String>

    @Query("SELECT storage FROM stories")
    fun getStorage(): List<String>

    @Query("SELECT chaptersize FROM stories")
    fun getChaptersize(): List<String>

    @Query("SELECT id FROM chapters WHERE storyId = :id")
    fun getIdChapter(id : String): List<String>

    @Query("SELECT bookId FROM chapters WHERE storyId = :id")
    fun getIdBook(id : String): List<String>

    @Query("SELECT name FROM chapters WHERE storyId = :id")
    fun getNameChapter(id : String): List<String>

    @Query("SELECT link FROM chapters WHERE storyId = :id")
    fun getLinkChapter(id : String): List<String>

    @Query("SELECT content FROM chapters WHERE storyId = :id")
    fun getHTMLChapter(id : String): List<String>

    @Query("SELECT contentString FROM chapters WHERE storyId = :id")
    fun getStringChapter(id : String): List<String>
}