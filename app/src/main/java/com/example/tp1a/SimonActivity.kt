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

        username.text = intent.(username)

        //liste du jeu
        val memoryList = ArrayList<Int>()

        //liste du joueur, à comparer avec la liste du Jeu
        val playerList = ArrayList<Int>()

        //Algo d'une partie
        while(playerList == memoryList) {
            //remise à 0 de la liste du joueur
            for (e in playerList)
                playerList.removeAt(e)

            // choix aléatoire d'un carré
            val simonRandomVal = Random.nextInt(1, nbCarrees + 1)
            memoryList.add(simonRandomVal)
            nbTours++

            for (e in memoryList) {
                if (e==1) {
                    btn1.setBackgroundColor(Color.RED)
                    Thread.sleep(1000)
                    btn1.setBackgroundResource(R.drawable.border)
                    Thread.sleep(500)
                }
                else if (e==2) {
                    btn2.setBackgroundColor(Color.RED)
                    Thread.sleep(1000)
                    btn1.setBackgroundResource(R.drawable.border)
                    Thread.sleep(500)
                }
                else if (e==3) {
                    btn3.setBackgroundColor(Color.RED)
                    Thread.sleep(1000)
                    btn1.setBackgroundResource(R.drawable.border)
                    Thread.sleep(500)
                }
                else {
                    btn4.setBackgroundColor(Color.RED)
                    Thread.sleep(1000)
                    btn1.setBackgroundResource(R.drawable.border)
                    Thread.sleep(500)
                }
            }

            for (i in 0..nbTours) {
                btn1.setOnClickListener() {
                    playerList.add(1)
                }
                btn2.setOnClickListener() {
                    playerList.add(2)
                }
                btn3.setOnClickListener() {
                    playerList.add(3)
                }
                btn4.setOnClickListener() {
                    playerList.add(4)
                }
                if (playerList[i] != memoryList[i])
                    break
            }
        }
    }
}

