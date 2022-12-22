package com.example.sportshopapplication.view.fragment

import android.content.Context
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.sportshopapplication.adapter.HoaDonAdapter
import com.example.sportshopapplication.databinding.FragmentShowdonhangBinding
import com.example.sportshopapplication.model.Item
import com.example.sportshopapplication.model.Receipt
import com.example.sportshopapplication.model.User
import com.example.sportshopapplication.view.activity.LoginActivity
import com.proxglobal.worlcupapp.base.BaseFragment
import org.json.JSONObject

class ShowDonHangFragment:BaseFragment<FragmentShowdonhangBinding>() {
    var adapter = HoaDonAdapter()
    var idTk : Int = 0
    override fun getDataBinding(): FragmentShowdonhangBinding {
        return FragmentShowdonhangBinding.inflate(layoutInflater)
    }

    override fun addEvent() {
        super.addEvent()
        binding.imageView7.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun initView() {
        super.initView()
        binding.recDonHang.adapter = adapter
        readUserList(requireContext(),LoginActivity.userEmail)

        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recDonHang.layoutManager = linearLayoutManager
    }

    private fun readHoaDonList(context: Context?) {
        var listItemSale = ArrayList<Receipt>()
        val url = "http://192.168.164.207/DoAn/hoadon/selectuser.php"
        val requestQueue: RequestQueue = Volley.newRequestQueue(context)
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                var jsonObject: JSONObject
                try {
                    for (i in 0..response.length()-1) {
                        jsonObject = response!!.getJSONObject(i)
                        var maHoaDon = jsonObject.getString("maHoaDon").toInt()
                        var maSanPham = jsonObject.getString("maSanPham")
                        var maTaiKhoan = jsonObject.getString("maTaiKhoan").toInt()
                        var soLuong = jsonObject.getString("soluong").toInt()
                        var gia = jsonObject.getString("gia")
                        var trangThai = jsonObject.getString("trangthai")
                        var pThucThanhToan = jsonObject.getString("pThucThanhToan")
                        var receipt = Receipt(maHoaDon,maSanPham,maTaiKhoan,soLuong,gia,trangThai,pThucThanhToan)
                        if (maTaiKhoan==idTk){
                            listItemSale.add(receipt)
                            Log.e("receipt",receipt.toString())
                        }
                        adapter.setCategoryList(listItemSale,requireContext())
                    }


                } catch (exception: Exception) {
                    Log.e("jsonObject", exception.toString())
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Log.e("jsonObject", error.toString())
                }
            })
        requestQueue.add(jsonArrayRequest)
    }

    fun readUserList(context: Context?, email1: String) {
        val url = "http://192.168.164.207/DoAn/user/selectUser.php"
        val requestQueue: RequestQueue = Volley.newRequestQueue(context)
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                var jsonObject: JSONObject
                try {
                    for (i in 0..response.length()-1) {
                        jsonObject = response!!.getJSONObject(i)
                        var maTaiKhoan = jsonObject.getString("maTaiKhoan").toInt()
                        var tenKhachHang = jsonObject.getString("tenKhachHang")
                        var daichi = jsonObject.getString("diachi")
                        var sdt = jsonObject.getString("sdt")
                        var email = jsonObject.getString("email")
                        var anh = jsonObject.getString("anh")
                        var matKhau = jsonObject.getString("matKhau")
                        if (email.equals(email1)){
                            var user = User(maTaiKhoan,tenKhachHang,daichi,sdt,email,anh,matKhau)
                            idTk = user.maTaiKhoan
                            readHoaDonList(requireContext())
                        }
                    }

                } catch (exception: Exception) {
                    Log.e("jsonObject", exception.toString())
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Log.e("jsonObject", error.toString())
                }
            })
        requestQueue.add(jsonArrayRequest)
    }
}