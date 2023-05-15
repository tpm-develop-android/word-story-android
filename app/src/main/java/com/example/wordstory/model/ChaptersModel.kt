package com.example.wordstory.model

import java.io.Serializable

data class ChaptersModel (
    val id: String,

    val storyId: String,

    val bookId: String,

    val name: String,
    val link: String,
    val content: String,
    val contentString: String
) : Serializable