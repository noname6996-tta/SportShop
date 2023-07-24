package com.example.sportshopapplication.view.activity

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.sportshopapplication.databinding.ActivityLoginBinding
import com.example.sportshopapplication.ui.base.BaseActivity
import com.example.sportshopapplication.util.Constants.Companion.URL_LOGIN
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    val KEY_USERNAME = "email"
    val KEY_PASSWORD = "matKhau"

    companion object {
        var userEmail: String = ""
    }

    override fun getDataBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun initData() {
        super.initData()
    }

    override fun addEvent() {
        super.addEvent()
        binding.tvDangky.setOnClickListener {

        }
        binding.btnLogin.setOnClickListener {
            var email = binding.edtEmail.text.toString().trim()
            var password = binding.edtPassword.text.toString().trim()
            if (checkEditText(email) && checkEditText(password)) {
                checkLogin(email, password, this)
            }
        }
        binding.tvDangky.setOnClickListener {
            val intent = Intent(this@LoginActivity, DangKyActivity::class.java)
            startActivity(intent)
        }
    }


    fun checkEditText(editText: String): Boolean {
        if (editText.length > 0) return true else {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
        }
        return false
    }

    fun checkLogin(email1: String, password1: String, context: Context?) {
        val requestLogin: StringRequest = object : StringRequest(
            Method.POST, URL_LOGIN,
            Response.Listener { response ->
                try {
                    val jsonObject = JSONObject(response)
                    if (jsonObject.getInt("success") == 1) {
                        var email = jsonObject.getString("email")
                        userEmail = email
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Login fail", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                VolleyLog.d(
                    ContentValues.TAG,
                    "Error: " + error.message
                )
            }) {
            override fun getParams(): Map<String, String>? {
                val params: MutableMap<String, String> = HashMap()
                params[KEY_USERNAME] = email1
                params[KEY_PASSWORD] = password1
                return params
            }
        }
        val queue = Volley.newRequestQueue(context)
        queue.add(requestLogin)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setMessage("Do you want to Exit?")
        builder.setPositiveButton("Yes") { dialog, which -> //if user pressed "yes", then he is allowed to exit from application
            this.finishAffinity();
        }
        builder.setNegativeButton("No") { dialog, which -> //if user select "No", just cancel this dialog and continue with app
            dialog.cancel()
        }
        val alert = builder.create()
        alert.show()
    }
}