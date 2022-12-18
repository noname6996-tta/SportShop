package com.example.sportshopapplication.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sportshopapplication.databinding.ActivityLoginBinding
import com.example.sportshopapplication.ui.base.BaseActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(){
    override fun getDataBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }
}