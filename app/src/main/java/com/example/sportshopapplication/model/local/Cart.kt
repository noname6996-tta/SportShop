package com.example.sportshopapplication.model.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "cart_table")
data class Cart(
    @PrimaryKey(autoGenerate = true)
    var maDonHang : Int ,
    var maSanPham : Int ,
    var maKhachHang: Int,
    var tenSanPham : String,
    var soLuong: Int,
    var gia: String,
    var anh : String,
) : Parcelable