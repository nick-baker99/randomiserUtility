package com.example.randomiserutilityapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayoutStates
import com.google.android.material.bottomnavigation.BottomNavigationView

class CoinFlip : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_flip)

        //store the flip button as a variable
        val flipButton = findViewById<Button>(R.id.flipBtn)
        //store the coin image view as a variable
        val coinImage = findViewById<ImageView>(R.id.coinImage)
        //set the on click listener for the button
        flipButton.setOnClickListener {
            //when the button is pressed animate the image
            coinImage.animate().apply {
                //set animation duration to half a second
                duration = 500
                //rotate X by 3600
                rotationXBy(3600f)
            }
            //call the flip coin function and pass the coin image to it
            flipCoin(coinImage)
            //if the coin has already been flipped change button text to flip again
            if(flipButton.getText() == "Flip") {
                //change text
                flipButton.setText("Flip Again")
                //change font size
                flipButton.setTextSize(16f)
            }
        }
        //store the navbar menu as a variable
        val bottomNav = findViewById<BottomNavigationView>(R.id.navbar)
        //set the navbar listener for when an item is selected
        bottomNav.setOnNavigationItemSelectedListener { item ->
            //check what item was selected based on the id
            when (item.itemId) {
                //if coin flip item selected
                R.id.coinFlipBtn -> {
                    //start the coin flip activity
                    val intent = Intent(this, CoinFlip::class.java)
                    startActivity(intent)
                }
                //dice
                R.id.diceRollMenuBtn -> {
                    //start the dice activity
                    val intent = Intent(this, DiceRoll::class.java)
                    startActivity(intent)
                }
                //password generator
                R.id.pwMenuBtn -> {
                    //start the password generator acitivty
                    val intent = Intent(this, PasswordGenerator::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }
    //this function generates what side the coin will land on
    private fun flipCoin(image: ImageView) {
        //store the coin result text view in a variable
        val coinResult = findViewById<TextView>(R.id.coinResult)
        //generate a number, either 1 or 2 to decide what side the coin lands on
        when ((1..2).random()) {
            1 -> {
                //if the number is 1 set the image to the heads images
                image.setImageResource(R.drawable.coin1)
                //change result text to heads
                coinResult.setText("Heads!")
            }
            else -> {
                //if number is 2 set image to tails coin
                image.setImageResource(R.drawable.coin2)
                //change result to tails
                coinResult.setText("Tails!")
            }
        }
    }
}
