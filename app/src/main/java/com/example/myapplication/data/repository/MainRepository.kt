package com.example.myapplication.data.repository

import com.example.myapplication.data.api.APIHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: APIHelper) {

    suspend fun getFriends() =  apiHelper.getFriends()

}