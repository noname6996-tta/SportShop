package com.example.sportshopapplication.view.fragment

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.*
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.sportshopapplication.R
import com.example.sportshopapplication.adapter.ItemCartAdapter
import com.example.sportshopapplication.databinding.FragmentBuyBinding
import com.example.sportshopapplication.model.Receipt
import com.example.sportshopapplication.model.User
import com.example.sportshopapplication.view.activity.LoginActivity
import com.example.sportshopapplication.viewmodel.CartViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.proxglobal.worlcupapp.base.BaseFragment
import org.json.JSONObject

class BuyFragment:BaseFragment<FragmentBuyBinding>() {
    var tenMatHang : String  = ""
    var idTk : Int = 0
    var tongTien : Int = 0
    var soLuong : Int = 0
    var cartItemAdapter = ItemCartAdapter()
    override fun getDataBinding(): FragmentBuyBinding {
        return FragmentBuyBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()
        val musicPlayListViewModel = ViewModelProvider(this)[CartViewModel::class.java]
        binding.recyclerView.adapter = cartItemAdapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = linearLayoutManager
        musicPlayListViewModel.readAllData.observe(viewLifecycleOwner, Observer {playlist ->
            cartItemAdapter.setItemList(playlist,requireContext())
            for (i in 0..playlist.size-1){
                tongTien = tongTien + playlist[i].gia.toString().toInt()
                soLuong = soLuong + playlist[i].soLuong.toString().toInt()
                tenMatHang = tenMatHang + playlist[i].tenSanPham + "-"
            }
            binding.tvGiaThanhToan.text = tongTien.toString() + "đ"
            readUserList(requireContext(),LoginActivity.userEmail)
        })
    }

    override fun initData() {
        super.initData()
        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.viewThanhToan.setOnClickListener {
            var ptThanhToan : String = binding.tvPhuongThucThanhToan.text.toString()
            var hoadon = Receipt(0,tenMatHang,idTk,soLuong,tongTien.toString(),"Chờ xác nhận",ptThanhToan)
            addHoaDon(hoadon,requireContext())
        }
        binding.spinnerPTthanhtoan.setOnClickListener {
            val bottomSheetDialogSong = BottomSheetDialog(this.requireContext())
            bottomSheetDialogSong.setContentView(R.layout.bottom_sheet_choose_pay)
            bottomSheetDialogSong.show()

            var payThanhToanOff = bottomSheetDialogSong.findViewById<View>(R.id.viewThanhToanOff)
            payThanhToanOff?.setOnClickListener {
                binding.tvPhuongThucThanhToan.text = "Thanh toán Offline"
            }

            var payThanhToanOnline = bottomSheetDialogSong.findViewById<View>(R.id.viewThanhToanOnl)
            payThanhToanOnline?.setOnClickListener {
                binding.tvPhuongThucThanhToan.text = "Thanh toán Online"
            }
        }
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
                            binding.tvnameuser.text = user.tenKhachHang
                            binding.tvPhoneUser.text =user.sdt
                            binding.tvAddressUser.text = user.diachi
                            idTk = user.maTaiKhoan
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

    private fun addHoaDon(hoadon:Receipt, mContext: Context) {
        val url = "http://192.168.164.207/DoAn/hoadon/insertHoadon.php"
        val requestQueue = Volley.newRequestQueue(mContext)
        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener {
                Toast.makeText(
                    requireContext(),
                    "Đặt hàng thành công, bạn có thể vào mục xem đơn hàng để kiểm tra đơn hàng",
                    Toast.LENGTH_SHORT
                ).show()
            },
            Response.ErrorListener { }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String>? {
                val map: MutableMap<String, String> = HashMap()
                map["maSanPham"] = hoadon.maSanPham.toString()
                map["maTaiKhoan"] = hoadon.maTaiKhoan.toString()
                map["soluong"] = hoadon.soLuong.toString()
                map["gia"] = hoadon.gia.toString()
                map["trangthai"] = hoadon.trangThai.toString()
                map["pThucThanhToan"] = hoadon.pThucThanhToan.toString()
                return map
            }
        }
        requestQueue.add(stringRequest)
    }

}