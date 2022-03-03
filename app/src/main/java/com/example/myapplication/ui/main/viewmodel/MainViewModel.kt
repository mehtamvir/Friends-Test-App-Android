package com.example.myapplication.ui.main.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.repository.MainRepository
import com.example.myapplication.utils.NetworkHelper
import com.example.myapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _friends = MutableLiveData<Resource<Friend>>()
    val friends: LiveData<Resource<Friend>>
        get() = _friends

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _friends.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getFriends().let {
                    if (it.isSuccessful) {
                        _friends.postValue(Resource.success(it.body()))
                    } else _friends.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _friends.postValue(Resource.error("No internet connection", null))
        }
    }
}