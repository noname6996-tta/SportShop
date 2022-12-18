package com.example.sportshopapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sportshopapplication.model.local.FavoriteItem

@Dao
interface FavoriteItemItemsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavoriteItem(FavoriteItem: FavoriteItem)

    @Update
    suspend fun updateFavoriteItem(FavoriteItem: FavoriteItem)

    @Delete
    suspend fun deleteFavoriteItem(FavoriteItem: FavoriteItem)

    @Query("DELETE FROM favoriteItem_table")
    suspend fun deleteAllFavoriteItem()

    @Query("SELECT * FROM favoriteItem_table")
    fun readAllData(): LiveData<List<FavoriteItem>>
}