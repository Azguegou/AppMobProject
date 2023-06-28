package com.example.tp1a

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        // get references
        val connexion = findViewById<Button>(R.id.Connexion)
        val username = findViewById<EditText>(R.id.Identifiant)
        val password = findViewById<EditText>(R.id.ConfirmMotDePasse)
        val logo = findViewById<ImageView>(R.id.imageView2)
        val inscription = findViewById<Button>(R.id.goToRegister)

        // set on-click listener
        inscription.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        connexion.setOnClickListener {
            BD.userExists(username.text.toString(), password.text.toString()).thenAccept { exists ->
                if (exists) {
                    Toast.makeText(this@LoginActivity, "Connexion réussie !", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this@LoginActivity, SimonActivity::class.java)
                    intent.putExtra("username", username.text.toString())
                    BD.getUserScore(username.text.toString()).thenAccept {score ->
                        intent.putExtra("score", score)
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Connexion échouée.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        logo.setOnClickListener {
            Toast.makeText(
                this@LoginActivity,
                "On n'a pas encore le déverouillage par empreinte, sorry.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}