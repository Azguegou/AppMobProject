package com.example.tp1a

import android.content.Intent
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
    private lateinit var btnDeco: Button

    private val memoryList: ArrayList<Int> = ArrayList()
    private val playerList: ArrayList<Int> = ArrayList()

    private var nbTours : Int = 1

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
        btnDeco = findViewById(R.id.buttonDeco)

        // Dans la fonction onCreate()
        btn1.isEnabled = false
        btn2.isEnabled = false
        btn3.isEnabled = false
        btn4.isEnabled = false


        btnJouer.setOnClickListener() {
            party()
        }
        btnDeco.setOnClickListener() {
            val intent = Intent(this@SimonActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    fun party() {
        val nbCarrees = 4
        nbTours += 1

        // Effacer la liste du joueur pour la prochaine manche
        playerList.clear()


        // Générez les valeurs aléatoires dans memoryList
        memoryList.add(Random.nextInt(1, nbCarrees + 1))


        //Toast.makeText(
        //    this@SimonActivity,
        //    "Retenez cette suite",
        //    Toast.LENGTH_SHORT
        //).show()

        clignoterBoutons(memoryList)

        Handler(Looper.getMainLooper()).postDelayed({
            Toast.makeText(
                this@SimonActivity,
                "À vous de jouer !",
                Toast.LENGTH_SHORT
            ).show()

            enableButtonInput()
        }, (1500L))


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
                        it.setBackgroundColor(Color.parseColor("#B1B2FF"))

                    }, delay/2)
                }
            }, (i * delay) + delay)
        }
    }
    private fun enableButtonInput() {
        btn1.setOnClickListener {
            addToPlayerList(1)
        }
        btn2.setOnClickListener {
            addToPlayerList(2)
        }
        btn3.setOnClickListener {
            addToPlayerList(3)
        }
        btn4.setOnClickListener {
            addToPlayerList(4)
        }

        // Activer les boutons
        btn1.isEnabled = true
        btn2.isEnabled = true
        btn3.isEnabled = true
        btn4.isEnabled = true
    }

    private fun addToPlayerList(buttonIndex: Int) {
        playerList.add(buttonIndex)
        checkPlayerList()

    }

    private fun checkPlayerList() {
        var isCorrect = true
        for (i in playerList.indices) {
            if (playerList[i] != memoryList[i]) {
                isCorrect = false
                break
            }
        }
        if (!isCorrect) {
            //suite incorrecte
            Toast.makeText(
                this@SimonActivity,
                "Désolé, la séquence est incorrecte.",
                Toast.LENGTH_SHORT
            ).show()
            memoryList.clear()
            playerList.clear()
        } else if (playerList.size == memoryList.size) {
            //suite correte
            party()
            playerList.clear()
        }
    }

}
