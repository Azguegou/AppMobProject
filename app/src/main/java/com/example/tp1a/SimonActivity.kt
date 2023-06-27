package com.example.tp1a

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import kotlin.random.Random

class SimonActivity : AppCompatActivity() {

    // Déclarez ces variables en tant que propriétés de classe
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btnJouer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simon)

        // Initialisez les boutons
        btn1 = findViewById(R.id.simonButton1)
        btn2 = findViewById(R.id.simonButton2)
        btn3 = findViewById(R.id.simonButton3)
        btn4 = findViewById(R.id.simonButton4)
        btnJouer = findViewById(R.id.buttonJouer)

        btnJouer.setOnClickListener() {
            party()
        }
    }

    fun party() {
        val nbCarrees = 4
        var nbTours = 5

        // Liste du jeu
        val memoryList = ArrayList<Int>()

        // Générez les valeurs aléatoires dans memoryList
        //TODO : enlever cette boucle for à la fin du projet
        for (i in 1..nbTours) {
            memoryList.add(Random.nextInt(1, nbCarrees + 1))
        }

        Toast.makeText(
            this@SimonActivity,
            "Retenez cette suite",
            Toast.LENGTH_SHORT
        ).show()

        clignoterBoutons(memoryList)

        Toast.makeText(
            this@SimonActivity,
            "à vous de jouer !",
            Toast.LENGTH_SHORT
        ).show()
    }



    fun clignoterBoutons(memoryList: ArrayList<Int>) {
        val delay = 1000L // délai de 1000ms
        val handler = Handler(Looper.getMainLooper())

        for (i in memoryList.indices) {

            //selection des bouttons en fonction de l'indice dans la liste
            val button = when (memoryList[i]) {
                1 -> btn1
                2 -> btn2
                3 -> btn3
                4 -> btn4
                else -> null
            }

            handler.postDelayed({
                button?.let {
                    it.setBackgroundColor(Color.WHITE)
                    handler.postDelayed({
                        it.setBackgroundColor(Color.parseColor("#B1B2FF"));
                    }, delay/2)
                }
            }, (i * delay) + delay)
        }
    }
}
