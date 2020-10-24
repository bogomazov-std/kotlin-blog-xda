package com.example.apiblog

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BlogServices {

    @GET("/")
    suspend fun getCurrentBlog(
        @Query("page") page: Int,
        @Query("count") count: Int,
        @Query("json") json: Int = 1,
    ): DataBlog
}

interface CellClickListener {
    fun onCellClickListener(pos :String?)
}