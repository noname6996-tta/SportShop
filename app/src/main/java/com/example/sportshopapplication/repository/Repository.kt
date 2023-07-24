package com.example.sportshopapplication.repository

import com.example.sportshopapplication.model.User
import com.example.sportshopapplication.model.local.Cart
import com.example.sportshopapplication.network.RetrofitInstance

class Repository {
    suspend fun getUserInfo() : User {
        return RetrofitInstance.api.getUser()
    }
}