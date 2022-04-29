package com.atillaeren.tictactoeminimax

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnPlayer.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // To pass any data to next activity
            intent.putExtra("Mode", 0)
            startActivity(intent)
        }
        btnAi.setOnClickListener {
            buttonVisibility()
        }
        btnEasy.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // To pass any data to next activity
            intent.putExtra("Mode", 1)
            startActivity(intent)
        }
        btnHell.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // To pass any data to next activity
            intent.putExtra("Mode", 2)
            startActivity(intent)
        }
    }
    private fun buttonVisibility() {
        btnEasy.visibility = View.VISIBLE
        btnEasy.isClickable = true

        btnHell.visibility = View.VISIBLE
        btnHell.isClickable = true

        btnPlayer.visibility = View.GONE
        btnPlayer.isClickable = false

        btnAi.visibility = View.GONE
        btnAi.isClickable = false
    }
}