package com.example.sportshopapplication.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.sportshopapplication.databinding.ActivitySplashBinding
import com.example.sportshopapplication.ui.base.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(){
    override fun getDataBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun addEvent() {
        super.addEvent()
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
        }, 1500)
    }
}