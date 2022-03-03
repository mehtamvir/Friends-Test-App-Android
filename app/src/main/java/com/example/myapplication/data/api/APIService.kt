package com.example.myapplication.data.api

import com.example.myapplication.data.model.Friend
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET("?results=10")
    suspend fun getFriends(): Response<Friend>

}