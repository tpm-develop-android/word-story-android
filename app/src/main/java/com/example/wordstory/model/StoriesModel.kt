package com.example.wordstory.model

import java.io.Serializable

data class StoriesModel(
    val id: String,

    val name: String?,

    val image: String?,

    val author: String?,
    val summary: String?,
    val link: String?,
    val path: String?,
    val storage: String,
    val chaptersize: String?
) : Serializable