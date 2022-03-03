package com.example.myapplication.data.api

import com.example.myapplication.data.model.Friend
import retrofit2.Response
import javax.inject.Inject

class APIHelperImpl @Inject constructor(private val apiService: APIService) : APIHelper {

    override suspend fun getFriends(): Response<Friend> = apiService.getFriends()

}