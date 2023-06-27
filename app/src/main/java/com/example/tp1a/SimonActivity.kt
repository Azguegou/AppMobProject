package com.example.tp1a

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import kotlin.random.Random

class SimonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simon)

        val btn1 = findViewById<Button>(R.id.simonButton1)
        val btn2 = findViewById<Button>(R.id.simonButton2)
        val btn3 = findViewById<Button>(R.id.simonButton3)
        val btn4 = findViewById<Button>(R.id.simonButton4)

        party()

    }

    fun party() {
        val nbCarrees = 4;
        var nbtours = 0;

        //liste du jeu
        val memoryList = ArrayList<Int>()
        val randomValues = Random.nextInt(1, nbCarrees + 1);
        memoryList.add(randomValues)

        //liste du joueur, à comparer avec la liste du Jeu
        val playerList = ArrayList<Int>();
        //TODO : setOnClickListerer dans une fonction pour récupérer les id du bouton cliqué par le joueur
    }
}