package com.example.wordstory.database

import androidx.room.Entity
import androidx.room.Index

@Entity(tableName = "chapters", primaryKeys = ["id"], indices = [Index(name = "name_UNIQUE", value = ["name"], unique = true), Index(name = "idx_name", unique = true, value = ["name"])])
data class ChaptersEntity(
    val id: String,

    val storyId: String,

    val bookId: String,

    val name: String,
    val link: String,
    val content: String,
    val contentString: String,
    val TRIAL455: String?
)
