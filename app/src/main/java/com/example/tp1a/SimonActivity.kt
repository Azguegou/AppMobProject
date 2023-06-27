package com.example.tp1a

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

class SimonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simon)

        // get references
        val btnDisconnect = findViewById<Button>(R.id.Deconnexion)
        val textName = findViewById<TextView>(R.id.textView)

        textName.setText(intent.getStringExtra("username"))

        // set on-click listener
        btnDisconnect.setOnClickListener {
            Toast.makeText(this@SimonActivity, "Au revoir !", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@SimonActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}