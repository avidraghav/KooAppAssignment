package com.example.kooappassignment.repository

import com.example.kooappassignment.data.network.SampleApi
import com.example.kooappassignment.utils.SafeApiCall
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val sampleApi: SampleApi
) : SafeApiCall {
    suspend fun getPosts() = safeApiCall { sampleApi.getPosts() }
}