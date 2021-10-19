package com.example.randomiserutilityapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //store the 3 buttons on the main menu in variables
        val coinFlipButton = findViewById<Button>(R.id.coinFlipBtn)
        val rollDiceButton = findViewById<Button>(R.id.diceRollBtn)
        val passGenBtn = findViewById<Button>(R.id.pwGenBtn)

        //set onclick listener for the coin flip button
        coinFlipButton.setOnClickListener {
            //if button is pressed create a new intent for the coin flip acitivty
            val intent = Intent(this, CoinFlip::class.java)
            //start the activity
            startActivity(intent)
        }
        //dice button listener
        rollDiceButton.setOnClickListener {
            val intent = Intent(this, DiceRoll::class.java)
            //start dice roll activity
            startActivity(intent)
        }
        //password gen button listener
        passGenBtn.setOnClickListener {
            val intent = Intent(this, PasswordGenerator::class.java)
            //start the password gen activity
            startActivity(intent)
        }
    }
}