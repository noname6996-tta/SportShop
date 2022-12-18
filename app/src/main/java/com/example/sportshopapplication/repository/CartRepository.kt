package com.example.sportshopapplication.repository

import androidx.lifecycle.LiveData
import com.example.sportshopapplication.data.dao.CartDao
import com.example.sportshopapplication.model.local.Cart

class CartRepository(private val cartDao : CartDao) {

    val readAllData : LiveData<List<Cart>> = cartDao.readAllData()

    suspend fun addCart(Cart: Cart){
        cartDao.addCart(Cart)
    }

    suspend fun updateCart(Cart: Cart){
        cartDao.updateCart(Cart)
    }

    suspend fun deleteCart(Cart: Cart){
        cartDao.deleteCart(Cart)
    }

    suspend fun deleteAllCart(){
        cartDao.deleteAllCart()
    }
}