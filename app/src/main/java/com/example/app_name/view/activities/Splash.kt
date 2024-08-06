package com.example.app_name.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.app_name.R
import com.example.app_name.view.activities.base.BaseActivity

class Splash : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            val mIntent = Intent(this@Splash, MainActivity::class.java)
            startActivity(mIntent)
            finish()
        }, 2000)

    }

}