package com.example.sportshopapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.sportshopapplication.data.database.LocalDatabase
import com.example.sportshopapplication.model.Item
import com.example.sportshopapplication.model.local.Cart
import com.example.sportshopapplication.model.local.FavoriteItem
import com.example.sportshopapplication.repository.CartRepository
import com.example.sportshopapplication.repository.FavoriteItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData : LiveData<List<Cart>>
    private val cartRepository: CartRepository
    
    init {
        val cartDao = LocalDatabase.getDatabase(application).cartDao()
        cartRepository = CartRepository(cartDao)
        readAllData = cartRepository.readAllData
    }

    fun addCart(playList: Cart) {
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.addCart(playList)
        }
    }

    fun updateCart(playList: Cart) {
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.updateCart(playList)
        }
    }

    fun deleteCart(playList: Cart) {
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.deleteCart(playList)
        }
    }

    fun deleteAllCart() {
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.deleteAllCart()
        }
    }
}

class FavoriteItemViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData : LiveData<List<FavoriteItem>>
    private val favoriteItemRepository: FavoriteItemRepository

    init {
        val cartDao = LocalDatabase.getDatabase(application).favoriteItemDao()
        favoriteItemRepository = FavoriteItemRepository(cartDao)
        readAllData = favoriteItemRepository.readAllData
    }

    fun addCart(playList: FavoriteItem) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteItemRepository.addFavoriteItem(playList)
        }
    }

    fun updateCart(playList: FavoriteItem) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteItemRepository.updateFavoriteItem(playList)
        }
    }

    fun deleteCart(maSanPham: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteItemRepository.deleteFavoriteItem(maSanPham)
        }
    }

    fun deleteAllCart() {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteItemRepository.deleteAllFavoriteItem()
        }
    }
}