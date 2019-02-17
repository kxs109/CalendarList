package com.example.bao.calendarlist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val btnStart = findViewById<Button>(R.id.start_time)
        val btnEnd = findViewById<Button>(R.id.start_time)
        btnStart.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        btnEnd.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
    }
}