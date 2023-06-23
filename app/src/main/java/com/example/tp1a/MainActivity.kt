package com.example.tp1a

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

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
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
        btn.setOnClickListener {
            if (id.length() > 0 && password.length() > 0) {
                if (password.length() < 8) {
                    Toast.makeText(this@MainActivity, "Le mot de passe doit faire au moins 8 caractères.", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this@MainActivity, "Connexion réussie !", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@MainActivity, ProfilActivity::class.java)
                    intent.putExtra("username", id.text.toString())
                    startActivity(intent)
                }
            }
            else {
                Toast.makeText(this@MainActivity, "Connexion échouée.", Toast.LENGTH_SHORT).show()
            }
        }

        logo.setOnClickListener{
            Toast.makeText(this@MainActivity, "On a pas encore le déverouillage par empreinte, sorry.", Toast.LENGTH_SHORT).show()
        }
    }

}