package com.example.myapplication.data.api

import com.example.myapplication.data.model.Friend
import retrofit2.Response

interface APIHelper {
    suspend fun getFriends(): Response<Friend>
}