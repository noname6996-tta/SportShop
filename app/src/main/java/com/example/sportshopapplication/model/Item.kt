package com.example.sportshopapplication.model

import android.os.Parcelable

data class Item(
    var maSanPham : Int,
    var maDanhMuc : Int,
    var tenSanPham : String,
    var soLuong: Int,
    var gia: String,
    var anh : String,
    var moTa : String,
    var gioiTinh : String
) : java.io.Serializable
