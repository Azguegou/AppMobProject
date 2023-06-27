package com.example.tp1a

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import kotlin.random.Random

class SimonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simon)

        party()

    }

    fun party() {
        val nbCarrees = 4
        var nbTours = 0

        //variables locales des boutons du Simon
        val btn1 = findViewById<Button>(R.id.simonButton1)
        val btn2 = findViewById<Button>(R.id.simonButton2)
        val btn3 = findViewById<Button>(R.id.simonButton3)
        val btn4 = findViewById<Button>(R.id.simonButton4)
        val username = findViewById<TextView>(R.id.playerName)
        val score = findViewById<TextView>(R.id.score)

        username.setText(intent.getStringExtra("username"))
        score.setText(0)

        //liste du jeu
        val memoryList = ArrayList<Int>()

        //liste du joueur, à comparer avec la liste du Jeu
        val playerList = ArrayList<Int>()


    }
}

