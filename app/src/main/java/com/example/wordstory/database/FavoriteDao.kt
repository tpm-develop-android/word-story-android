package com.example.wordstory.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Query("select * from stories")
    fun getAllFavouriteStories(): LiveData<MutableList<StoriesEntity>>

    @Insert(onConflict = REPLACE)
    fun insertStory(storiesEntity: StoriesEntity)

    @Delete
    fun deleteStory(storiesEntity: StoriesEntity)

    @Query("select * from stories where id = :id LIMIT 1")
    fun getStoryById(id: String): StoriesEntity?

}