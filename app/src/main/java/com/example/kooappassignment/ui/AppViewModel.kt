package com.example.kooappassignment.ui

import androidx.lifecycle.*
import com.example.kooappassignment.data.models.PostsResponse
import com.example.kooappassignment.repository.AppRepository
import com.example.kooappassignment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel(), LifecycleObserver {

    private val _postsListResponse: MutableLiveData<Resource<PostsResponse>> =
        MutableLiveData()
    val productListResponse: LiveData<Resource<PostsResponse>>
        get() = _postsListResponse

    init {
        getDealsList()
    }

    private fun getDealsList() = viewModelScope.launch {
        _postsListResponse.value = Resource.Loading
        _postsListResponse.value = repository.getPosts()
    }
}