package com.example.sportshopapplication.view.fragment

import android.content.Context
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.sportshopapplication.adapter.CategoryAdapter
import com.example.sportshopapplication.databinding.FragmentCategoryBinding
import com.example.sportshopapplication.databinding.FragmentHomeBinding
import com.example.sportshopapplication.model.Category
import com.example.sportshopapplication.model.Item
import com.example.sportshopapplication.view.fragment.ListAllitemsfragment.Companion.categoryName
import com.proxglobal.worlcupapp.base.BaseFragment
import org.json.JSONObject

class CategoryFragment: BaseFragment<FragmentCategoryBinding>() {
    var categoryAdapter = CategoryAdapter()
    override fun getDataBinding(): FragmentCategoryBinding {
        return FragmentCategoryBinding.inflate(layoutInflater)
    }

    override fun initData() {
        super.initData()

    }

    override fun initView() {
        super.initView()
        binding.recCategory.adapter = categoryAdapter
        binding.recCategory.layoutManager =
            GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
        readCategoryList(requireContext())
    }

    override fun addEvent() {
        super.addEvent()
        categoryAdapter.setClickShowMusic {
            categoryName = it.maDanhMuc
            var action = CategoryFragmentDirections.actionCategoryFragmentToListAllitemsfragment("itemCategory")
            findNavController().navigate(action)
        }
    }

    

    private fun readCategoryList(context: Context?) {
        var listItemSale = ArrayList<Category>()
        val url = "http://192.168.1.9/DoAn/category/getAllDanhmuc.php"
        val requestQueue: RequestQueue = Volley.newRequestQueue(context)
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                var jsonObject: JSONObject
                try {
                    for (i in 0..response.length()-1) {
                        jsonObject = response!!.getJSONObject(i)
                        var maSanPham = jsonObject.getString("maDanhMuc").toInt()
                        var tenDanhMuc = jsonObject.getString("tenDanhmuc")
                        var anh = jsonObject.getString("anh")
                        var category = Category(maSanPham,tenDanhMuc,anh)
                        listItemSale.add(category)
                    }
                    Log.d("readCategoryList",listItemSale.toString())
                    categoryAdapter.setCategoryList(listItemSale, requireContext())
                } catch (exception: Exception) {
                    Log.e("readCategoryList", exception.toString())
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Log.e("readCategoryList", error.toString())
                }
            })
        requestQueue.add(jsonArrayRequest)
    }
}