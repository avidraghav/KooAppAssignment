package com.example.kooappassignment.data.network

import com.example.kooappassignment.data.models.PostsResponse
import retrofit2.http.GET

interface SampleApi {

    @GET("posts")
    suspend fun getPosts(): PostsResponse

}