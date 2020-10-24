package com.example.apiblog

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataBlog(
    val posts: List<Kek>,
    val status: String,
    val count: Int,
    val count_total: Int,
    val pages: Int,
)

@JsonClass(generateAdapter = true)
data class Kek(
    val title_plain: String,
    val content: String,
)

