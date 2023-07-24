package com.example.sportshopapplication.view.activity

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.sportshopapplication.databinding.ActivityDangKyBinding
import com.example.sportshopapplication.ui.base.BaseActivity

class DangKyActivity : BaseActivity<ActivityDangKyBinding>() {

    override fun getDataBinding(): ActivityDangKyBinding {
        return ActivityDangKyBinding.inflate(layoutInflater)
    }

    override fun addEvent() {
        super.addEvent()
        binding.btnReges.setOnClickListener {
            var email = binding.edtRegerEmail.text?.trim().toString()
            var pass = binding.edtRegesPass.text?.trim().toString()
            if (email.length > 0 && pass.length > 0) {
                addUser(email, pass, this)
            }
        }

    }

    private fun addUser(email: String, pass: String, mContext: Context) {
        val url = "http://192.168.1.9/DoAn/user/insertUser.php"
        val requestQueue = Volley.newRequestQueue(mContext)
        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener {
                Toast.makeText(
                    this,
                    "Đăng ký thành công vui lòng đăng nhập lại",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this@DangKyActivity, LoginActivity::class.java)
                startActivity(intent)
            },
            Response.ErrorListener { }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String>? {
                val map: MutableMap<String, String> = HashMap()
                map["email"] = email
                map["matkhau"] = pass
                return map
            }
        }
        requestQueue.add(stringRequest)
    }


}