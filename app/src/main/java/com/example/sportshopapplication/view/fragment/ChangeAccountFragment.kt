package com.example.sportshopapplication.view.fragment

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.android.volley.*
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.sportshopapplication.databinding.FragmentChangeAccountBinding
import com.example.sportshopapplication.model.User
import com.example.sportshopapplication.util.Constants
import com.example.sportshopapplication.view.activity.LoginActivity
import com.example.sportshopapplication.view.activity.MainActivity
import com.proxglobal.worlcupapp.base.BaseFragment
import org.json.JSONException
import org.json.JSONObject

class ChangeAccountFragment : BaseFragment<FragmentChangeAccountBinding>() {
    lateinit var user :User
    override fun getDataBinding(): FragmentChangeAccountBinding {
        return FragmentChangeAccountBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()
        readUserList(requireContext(),LoginActivity.userEmail)
        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun addEvent() {
        super.addEvent()
        binding.btnAcceptChange.setOnClickListener {
            var name =  binding.edtName.text.toString().trim()
            var address =  binding.edtAddress.text.toString().trim()
            var pass =  binding.edtPassword.text.toString().trim()
            var sdt =  binding.edtPhone.text.toString().trim()
            var email =  binding.edtEmail.text.toString().trim()

            var userChange = User(user.maTaiKhoan,name,address,sdt,email,user.anh,pass)

            val builder = AlertDialog.Builder(context)
            builder.setPositiveButton("Yes"){_,_ ->
                updateAccount(userChange,requireContext())
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            }
            builder.setNegativeButton("No"){_,_ ->

            }
            builder.setTitle("Thay đổi thông tin ?")
            builder.setMessage("Để thay đổi thông tin cần đăng nhập lại bạn chắc chắn chứ??")
            builder.create().show()
        }
    }



    fun readUserList(context: Context?, email1: String) {
        val url = "http://192.168.1.9/DoAn/user/selectUser.php"
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
                            user = User(maTaiKhoan,tenKhachHang,daichi,sdt,email,anh,matKhau)
                            binding.edtName.setText(user.tenKhachHang)
                            binding.edtAddress.setText(user.diachi)
                            binding.edtPassword.setText(user.matKhau)
                            binding.edtPhone.setText(user.sdt)
                            binding.edtEmail.setText(user.email)
                            Log.d("email",email+"P"+user.toString())
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

    fun updateAccount(user: User,context: Context?) {
        val url = "http://192.168.1.9/DoAn/user/update.php"
        val requestLogin: StringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->
                Log.d("userChange",user.toString())
            },
            Response.ErrorListener { error ->
                VolleyLog.d(
                    ContentValues.TAG,
                    "Error: " + error.message
                )
            }) {
            override fun getParams(): Map<String, String>? {
                val params: MutableMap<String, String> = HashMap()
                params["maTaiKhoan"] = user.maTaiKhoan.toString()
                params["tenKhachHang"] = user.tenKhachHang.toString()
                params["diachi"] = user.diachi.toString()
                params["sdt"] = user.sdt.toString()
                params["email"] = user.email.toString()
                params["anh"] = user.anh.toString()
                params["matKhau"] = user.matKhau.toString()
                return params
            }
        }
        val queue = Volley.newRequestQueue(context)
        queue.add(requestLogin)
    }

}