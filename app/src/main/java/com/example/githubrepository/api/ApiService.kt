package com.example.githubrepository.api

import com.example.githubrepository.model.Trending
import com.example.githubrepository.utils.Constants.END_POINT
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(END_POINT)
    suspend fun getTrending():Response<Trending>
}