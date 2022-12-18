package com.example.sportshopapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sportshopapplication.model.local.Cart

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCart(cart: Cart)

    @Update
    suspend fun updateCart(Cart: Cart)

    @Delete
    suspend fun deleteCart(Cart: Cart)

    @Query("DELETE FROM cart_table")
    suspend fun deleteAllCart()

    @Query("SELECT * FROM cart_table")
    fun readAllData(): LiveData<List<Cart>>

}