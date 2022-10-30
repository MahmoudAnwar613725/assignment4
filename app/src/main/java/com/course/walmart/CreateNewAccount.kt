package com.course.walmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CreateNewAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_account)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}