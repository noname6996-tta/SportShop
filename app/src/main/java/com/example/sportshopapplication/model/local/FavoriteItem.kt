package com.example.sportshopapplication.model.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favoriteItem_table")
data class FavoriteItem(
    @PrimaryKey(autoGenerate = true)
    var maYeuThich : Int,
    var maSanPham : Int,
    var tenSanPham : String,
    var anh : String,
    var gia : String
) : Parcelable
