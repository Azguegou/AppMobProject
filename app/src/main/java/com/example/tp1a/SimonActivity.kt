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

    private lateinit var username: TextView
    private lateinit var score: TextView

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
        username = findViewById(R.id.username)
        score = findViewById(R.id.score)

        username.setText(intent.getStringExtra("username"))

        score.setText("Meilleur score : " + intent.getIntExtra("score", 0))

        disableButtons()

        btnJouer.setOnClickListener() {
            party()
        }
        btnDeco.setOnClickListener() {
            val intent = Intent(this@SimonActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    fun party() {
        disableButtons()

        val nbCarrees = 4
        nbTours++

        // Effacer la liste du joueur pour la prochaine manche
        playerList.clear()

        // Générez les valeurs aléatoires dans memoryList
        memoryList.add(Random.nextInt(1, nbCarrees + 1))

        clignoterBoutons(memoryList)
        val delay = 900L
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            enableButtonInput()
        }, (nbTours * delay))
    }

    private fun disableButtons() {
        btn1.isClickable = false
        btn2.isClickable = false
        btn3.isClickable = false
        btn4.isClickable = false
    }

    fun clignoterBoutons(memoryList: ArrayList<Int>) {
        disableButtons()
        val delay = 1000L // délai de 1000ms
        val handler = Handler(Looper.getMainLooper())

        for (i in memoryList.indices) {
            //selection des boutons en fonction de l'indice dans la liste
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
        val delay = 100L // délai de 1000ms
        val handler = Handler(Looper.getMainLooper())

        btn1.setOnClickListener {
            it.setBackgroundColor(Color.WHITE)
            addToPlayerList(1)
            handler.postDelayed({
                btn1.let {
                    handler.postDelayed({
                        it.setBackgroundColor(Color.parseColor("#B1B2FF"))

                    }, delay)
                }
            }, (1))
        }
        btn2.setOnClickListener {
            it.setBackgroundColor(Color.WHITE)
            addToPlayerList(2)
            handler.postDelayed({
                btn2.let {
                    handler.postDelayed({
                        it.setBackgroundColor(Color.parseColor("#B1B2FF"))

                    }, delay/2)
                }
            }, (delay))
        }
        btn3.setOnClickListener {
            it.setBackgroundColor(Color.WHITE)
            addToPlayerList(3)
            handler.postDelayed({
                btn3.let {
                    handler.postDelayed({
                        it.setBackgroundColor(Color.parseColor("#B1B2FF"))

                    }, delay/2)
                }
            }, (delay))
        }
        btn4.setOnClickListener {
            addToPlayerList(4)
            handler.postDelayed({
                btn4.let {
                    it.setBackgroundColor(Color.WHITE)
                    handler.postDelayed({
                        it.setBackgroundColor(Color.parseColor("#B1B2FF"))

                    }, delay/2)
                }
            }, (delay))
        }

        btn1.isClickable = true
        btn2.isClickable = true
        btn3.isClickable = true
        btn4.isClickable = true
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
            disableButtons()
            memoryList.clear()
            playerList.clear()
            nbTours = 1
        } else if (playerList.size == memoryList.size) {
            //suite correcte
            BD.getUserScore(username.text.toString()).thenAccept{oldScore ->
                if (nbTours - 2 > oldScore) {
                    BD.updateUserScore(username.text.toString(), nbTours - 2)
                    score.setText("Meilleur score : " + (nbTours - 2))
                }
            }
            party()
            playerList.clear()
        }
    }

}
