package com.example.githubrepository.repository

import com.example.githubrepository.api.ApiService
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService){
    suspend fun getTrending() = apiService.getTrending()
}