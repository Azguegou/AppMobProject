package com.example.tp1a

import android.content.Intent
import android.os.Bundle
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
        val btn = findViewById<Button>(R.id.Connexion)
        val id = findViewById<EditText>(R.id.Identifiant)
        val password = findViewById<EditText>(R.id.ConfirmMotDePasse)
        val logo = findViewById<ImageView>(R.id.imageView2)
        val inscription = findViewById<Button>(R.id.goToRegister)

        // set on-click listener
        inscription.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
        btn.setOnClickListener {
            if (id.length() > 0 && password.length() > 0) {
                if (password.length() < 8) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Le mot de passe doit faire au moins 8 caractères.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(this@LoginActivity, "Connexion réussie !", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this@LoginActivity, SimonActivity::class.java)
                    intent.putExtra("username", id.text.toString())
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this@LoginActivity, "Connexion échouée.", Toast.LENGTH_SHORT).show()
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