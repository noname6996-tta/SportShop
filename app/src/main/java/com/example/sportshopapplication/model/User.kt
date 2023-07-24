package com.example.sportshopapplication.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("maTaiKhoan")
    var maTaiKhoan: Int,
    @SerializedName("tenKhachHang")
    var tenKhachHang: String,
    @SerializedName("daichi")
    var diachi: String,
    @SerializedName("sdt")
    var sdt: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("anh")
    var anh: String,
    @SerializedName("matKhau")
    var matKhau: String,
)
