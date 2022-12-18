package com.example.sportshopapplication.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sportshopapplication.databinding.ActivitySplashBinding
import com.example.sportshopapplication.ui.base.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(){
    override fun getDataBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }
}