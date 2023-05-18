package com.example.wordstory.database

import androidx.room.Entity
import java.io.Serializable

@Entity(tableName = "stories", primaryKeys = ["id", "storage"])
data class StoriesEntity(
    val id: String,

    val name: String?,

    val image: String?,

    val author: String?,
    val summary: String?,
    val link: String?,
    val path: String?,
    val storage: String,
    val chaptersize: String?,
    val TRIAL455: String?
) : Serializable
