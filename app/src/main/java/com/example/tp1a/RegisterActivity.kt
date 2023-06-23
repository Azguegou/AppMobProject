package com.example.tp1a

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        // get references
        val username = findViewById<EditText>(R.id.Identifiant)
        val password = findViewById<EditText>(R.id.MotDePasse)
        val confirmPassword = findViewById<EditText>(R.id.ConfirmMotDePasse)
        val inscriptionButton = findViewById<Button>(R.id.inscription)
        val connectionButton = findViewById<Button>(R.id.connexion)

        connectionButton.setOnClickListener {
            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }
}