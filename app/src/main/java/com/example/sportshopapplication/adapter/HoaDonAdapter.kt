package com.example.sportshopapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportshopapplication.databinding.ItemDonhangBinding
import com.example.sportshopapplication.model.Receipt


class HoaDonAdapter : RecyclerView.Adapter<HoaDonViewHolder>() {
    private var musics: List<Receipt> = listOf()
    private lateinit var context: Context
    fun setCategoryList(movies: List<Receipt>, context: Context) {
        this.musics = movies.toMutableList()
        this.context = context
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HoaDonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDonhangBinding.inflate(inflater, parent, false)
        return HoaDonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HoaDonViewHolder, position: Int) {
        val music = musics[position]
//
//            val url = "http://192.168.164.207/DoAn/user/selectUser.php"
//            val requestQueue: RequestQueue = Volley.newRequestQueue(context)
//            val jsonArrayRequest = JsonArrayRequest(
//                Request.Method.GET, url, null,
//                { response ->
//                    var jsonObject: JSONObject
//                    try {
//                        for (i in 0..response.length()-1) {
//                            jsonObject = response!!.getJSONObject(i)
//                            var maTaiKhoan = jsonObject.getString("maTaiKhoan").toInt()
//                            var tenKhachHang = jsonObject.getString("tenKhachHang")
//                            var daichi = jsonObject.getString("diachi")
//                            var sdt = jsonObject.getString("sdt")
//                            var email = jsonObject.getString("email")
//                            var anh = jsonObject.getString("anh")
//                            var matKhau = jsonObject.getString("matKhau")
//                            if (email.equals(music.maTaiKhoan)){
//                                var user = User(maTaiKhoan,tenKhachHang,daichi,sdt,email,anh,matKhau)
        holder.binding.tvTenKhachhang.text = "Thế Anh"
        holder.binding.tvSdt.text = "0123456789"
        holder.binding.tvAddress.text = "Cầu giấy"
        holder.binding.tvGiaTien.text = music.gia
        holder.binding.tvDonHang.text = music.maSanPham
        holder.binding.tvTrangThai.text = music.trangThai
//
//                            }
//                        }
//                    } catch (exception: Exception) {
//                        Log.e("jsonObject", exception.toString())
//                    }
//                },
//                object : Response.ErrorListener {
//                    override fun onErrorResponse(error: VolleyError?) {
//                        Log.e("jsonObject", error.toString())
//                    }
//                })
//            requestQueue.add(jsonArrayRequest)


    }

    override fun getItemCount(): Int {
        return musics.size
    }
}

class HoaDonViewHolder(val binding: ItemDonhangBinding) :
    RecyclerView.ViewHolder(binding.root) {
}