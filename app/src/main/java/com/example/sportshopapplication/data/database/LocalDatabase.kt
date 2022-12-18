package com.example.sportshopapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sportshopapplication.data.dao.CartDao
import com.example.sportshopapplication.data.dao.FavoriteItemItemsDao
import com.example.sportshopapplication.model.local.Cart
import com.example.sportshopapplication.model.local.FavoriteItem

@Database(entities = [Cart::class,FavoriteItem::class], version = 1, exportSchema = false)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun cartDao() : CartDao
    abstract fun favoriteItemDao() : FavoriteItemItemsDao
    companion object {
        @Volatile
        private var INSTANCE : LocalDatabase? = null

        // chekc instance is not null
        fun getDatabase(context: Context):LocalDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                // if no instance create one
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "local_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}