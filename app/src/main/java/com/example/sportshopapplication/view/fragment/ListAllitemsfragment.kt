package com.example.sportshopapplication.view.fragment

import android.content.Context
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.sportshopapplication.adapter.HomeItemAdapter
import com.example.sportshopapplication.databinding.FragmentAllitemBinding
import com.example.sportshopapplication.model.Item
import com.proxglobal.worlcupapp.base.BaseFragment
import org.json.JSONObject

class ListAllitemsfragment:BaseFragment<FragmentAllitemBinding>() {
    var homeItemAdapter = HomeItemAdapter()
    private val args : ListAllitemsfragmentArgs by navArgs()
    override fun getDataBinding(): FragmentAllitemBinding {
        return FragmentAllitemBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()
        binding.recAllItem.adapter = homeItemAdapter
        binding.recAllItem.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

    }

    override fun addEvent() {
        super.addEvent()
        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun initData() {
        super.initData()
        when(args.type){
            "item" -> {
                readItemList(requireContext())
            }
            "sale" -> {
                readItemSaleList(requireContext())
            }
        }
    }

    private fun readItemSaleList(context: Context?) {
        val url = "http://192.168.164.207/DoAn/Saleitem/getAllISale.php"
        val requestQueue: RequestQueue = Volley.newRequestQueue(context)
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                var jsonObject: JSONObject
                try {
                    for (i in 0..7) {
                        jsonObject = response!!.getJSONObject(i)
                        var maKhuyenMai = jsonObject.getString("maKhuyenMai").toInt()
                        var maSanPham = jsonObject.getString("maSanPham").toInt()
                        var phanTramKM = jsonObject.getString("phanTramKM")
                        var giaSauKm = jsonObject.getString("giaSauKm")
                        readItemListSale(requireContext(), maSanPham)
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

    private fun readItemListSale(context: Context?, id: Int) {
        var listItemSale = ArrayList<Item>()
        val url = "http://192.168.164.207/DoAn/item/getAllItem.php"
        val requestQueue: RequestQueue = Volley.newRequestQueue(context)
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                var jsonObject: JSONObject
                try {
                    for (i in 0..response.length() - 1) {
                        jsonObject = response!!.getJSONObject(i)
                        var maSanPham = jsonObject.getString("maSanPham").toInt()
                        var maDanhMuc = jsonObject.getString("maDanhMuc").toInt()
                        var tenSanPham = jsonObject.getString("tenSanPham")
                        var soLuong = jsonObject.getString("soLuong").toInt()
                        var gia = jsonObject.getString("gia")
                        var anh = jsonObject.getString("anh")
                        var moTa = jsonObject.getString("moTa")
                        var gioiTinh = jsonObject.getString("gioiTinh")
                        var item = Item(
                            maSanPham,
                            maDanhMuc,
                            tenSanPham,
                            soLuong,
                            gia,
                            anh,
                            moTa,
                            gioiTinh
                        )
                        if (id == maSanPham) {
                            listItemSale.add(item)
                        }
                        homeItemAdapter.setItemList(listItemSale, requireContext())
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

    private fun readItemList(context: Context?) {
        var listItemSale = ArrayList<Item>()
        val url = "http://192.168.164.207/DoAn/item/getAllItem.php"
        val requestQueue: RequestQueue = Volley.newRequestQueue(context)
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                var jsonObject: JSONObject
                try {
                    for (i in 0..response.length()-1) {
                        jsonObject = response!!.getJSONObject(i)
                        var maSanPham = jsonObject.getString("maSanPham").toInt()
                        var maDanhMuc = jsonObject.getString("maDanhMuc").toInt()
                        var tenSanPham = jsonObject.getString("tenSanPham")
                        var soLuong = jsonObject.getString("soLuong").toInt()
                        var gia = jsonObject.getString("gia")
                        var anh = jsonObject.getString("anh")
                        var moTa = jsonObject.getString("moTa")
                        var gioiTinh = jsonObject.getString("gioiTinh")
                        var item = Item(
                            maSanPham,
                            maDanhMuc,
                            tenSanPham,
                            soLuong,
                            gia,
                            anh,
                            moTa,
                            gioiTinh
                        )
                        listItemSale.add(item)
                    }
                    homeItemAdapter.setItemList(listItemSale, requireContext())
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