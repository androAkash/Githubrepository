package com.example.githubrepository.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepository.model.TrendingItem
import com.example.githubrepository.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubTrendingViewModel
@Inject constructor(private val repository: Repository) : ViewModel() {

    private val _response = MutableLiveData<List<TrendingItem>>()
    val responseGithubTrending: LiveData<List<TrendingItem>>
        get() = _response

    init {
        getAllGithubTrending()
    }

    private fun getAllGithubTrending() = viewModelScope.launch {
        repository.getTrending().let { response ->

            if (response.isSuccessful){
                _response.postValue(response.body())
            } else{
                Log.d("apiFault", "getAllGithubTrending: ${response.errorBody()}")
            }
        }
    }

}