package com.example.sportshopapplication.repository

import com.example.sportshopapplication.model.local.Cart
import com.example.sportshopapplication.network.RetrofitInstance

class Repository {
    suspend fun getToplistenedCrotines() : Cart {
        return RetrofitInstance.api.getToplistenedCrotines()
    }
}