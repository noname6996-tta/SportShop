package com.example.sportshopapplication.repository

import androidx.lifecycle.LiveData
import com.example.sportshopapplication.data.dao.FavoriteItemItemsDao
import com.example.sportshopapplication.model.local.FavoriteItem

class FavoriteItemRepository(private val favoriteItemItemsDao: FavoriteItemItemsDao) {
    val readAllData : LiveData<List<FavoriteItem>> = favoriteItemItemsDao.readAllData()

    suspend fun addFavoriteItem(FavoriteItem: FavoriteItem){
        favoriteItemItemsDao.addFavoriteItem(FavoriteItem)
    }

    suspend fun updateFavoriteItem(FavoriteItem: FavoriteItem){
        favoriteItemItemsDao.updateFavoriteItem(FavoriteItem)
    }

    suspend fun deleteFavoriteItem(maSanPham: Int){
        favoriteItemItemsDao.deleteFavoriteItem(maSanPham)
    }

    suspend fun deleteAllFavoriteItem(){
        favoriteItemItemsDao.deleteAllFavoriteItem()
    }
}