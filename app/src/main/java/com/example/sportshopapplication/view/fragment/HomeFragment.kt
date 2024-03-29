package com.example.sportshopapplication.view.fragment

import android.content.Context
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.sportshopapplication.adapter.HomeDownloadAdapter
import com.example.sportshopapplication.adapter.HomeItemAdapter
import com.example.sportshopapplication.adapter.HomeItemSaleAdapter
import com.example.sportshopapplication.databinding.FragmentHomeBinding
import com.example.sportshopapplication.model.Item
import com.example.sportshopapplication.model.local.Photos
import com.proxglobal.worlcupapp.base.BaseFragment
import org.json.JSONObject

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var runnable: Runnable
    var homeImageAdapter = HomeDownloadAdapter()
    var homeItemAdapter = HomeItemAdapter()
    var homeItemSaleAdapter = HomeItemSaleAdapter()
    override fun getDataBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()
        initImageSale()
        setDataItem()
        setDataItemSale()
    }

    override fun initData() {
        super.initData()
    }

    override fun addEvent() {
        super.addEvent()
        binding.searchView.setOnClickListener {
            var action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            findNavController().navigate(action)
        }
        binding.tvMoreItem.setOnClickListener {
            var action = HomeFragmentDirections.actionHomeFragmentToListAllitemsfragment("item")
            findNavController().navigate(action)
        }
        binding.tvMoreSaleItem.setOnClickListener {
            var action = HomeFragmentDirections.actionHomeFragmentToListAllitemsfragment("sale")
            findNavController().navigate(action)
        }
    }

    private fun initImageSale() {
        binding.viewPager2.adapter = homeImageAdapter
        binding.viewPager2.offscreenPageLimit = 2
        binding.viewPager2.clipToPadding = false
        binding.viewPager2.clipChildren = false
        binding.viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        var photos = Photos(
            "data:image/jpeg;base64,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"
        )
        var photos2 = Photos(
            "data:image/jpeg;base64,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"
        )
        var photos3 = Photos(
            "data:image/jpeg;base64,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"
        )
        var arraylistPhotos = java.util.ArrayList<Photos>()
        arraylistPhotos.add(photos)
        arraylistPhotos.add(photos2)
        arraylistPhotos.add(photos3)
        homeImageAdapter.setPhotosList(arraylistPhotos, requireContext())
        runnable = kotlinx.coroutines.Runnable {
            if (binding.viewPager2.currentItem == arraylistPhotos.size - 1) {
                binding.viewPager2.currentItem = 0
            } else {
                binding.viewPager2.currentItem = binding.viewPager2.currentItem + 1
            }
        }
    }

    private fun setDataItemSale() {
        binding.recSanPhamKhuyenMai.adapter = homeItemSaleAdapter
        binding.recSanPhamKhuyenMai.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        readItemSaleList(requireContext())
        homeItemSaleAdapter.setClickShowMusic {
            var action = HomeFragmentDirections.actionHomeFragmentToItemFragment(it)
            findNavController().navigate(action)
        }
    }

    private fun setDataItem() {
        binding.recItem.adapter = homeItemAdapter
        binding.recItem.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        readItemList(requireContext())
        homeItemAdapter.setClickShowMusic {
            var action = HomeFragmentDirections.actionHomeFragmentToItemFragment(it)
            findNavController().navigate(action)
        }
    }

    private fun readItemList(context: Context?) {
        var listItemSale = ArrayList<Item>()
        val url = "http://192.168.1.9/DoAn/item/getAllItem.php"
        val requestQueue: RequestQueue = Volley.newRequestQueue(context)
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                var jsonObject: JSONObject
                try {
                    for (i in 0..5) {
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

    private fun readItemSaleList(context: Context?) {
        val url = "http://192.168.1.9/DoAn/Saleitem/getAllISale.php"
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
        val url = "http://192.168.1.9/DoAn/item/getAllItem.php"
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
                        homeItemSaleAdapter.setItemSaleList(listItemSale, requireContext())
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