package com.example.randomiserutilityapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

class DiceRoll : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice_roll)

        //store the navbar menu as a variable
        val bottomNav = findViewById<BottomNavigationView>(R.id.navbar)
        //set the listener for when an item is selected
        bottomNav.setOnNavigationItemSelectedListener { item ->
            //check what item is selected based on the id
            when (item.itemId) {
                //coin flip
                R.id.coinFlipBtn -> {
                    //start coin flip activity
                    val intent = Intent(this, CoinFlip::class.java)
                    startActivity(intent)
                }
                //dice
                R.id.diceRollMenuBtn -> {
                    //start dice activity
                    val intent = Intent(this, DiceRoll::class.java)
                    startActivity(intent)
                }
                //password gen
                R.id.pwMenuBtn -> {
                    //start password gen acticity
                    val intent = Intent(this, PasswordGenerator::class.java)
                    startActivity(intent)
                }
            }
            true
        }
        //store roll button as variable
        val rollButton = findViewById<Button>(R.id.rollBtn)
        //store the dice image view as a variable
        val diceImage = findViewById<ImageView>(R.id.diceImage)
        //set on click listener for the roll button
        rollButton.setOnClickListener {
            //when the button is pressed animate the image
            diceImage.animate().apply {
                //set the duration to half a second
                duration = 500
                //rotate X and Y by 1080
                rotationXBy(1080f)
                rotationYBy(1080f)
            }
            //call the dice roll function and pass the dice image
            diceRoll(diceImage)
            //if the dice has been rolled for the first time change the button text to roll again
            if(rollButton.getText() == "Roll") {
                //change text
                rollButton.setText("Roll Again")
                //change font size
                rollButton.setTextSize(16f)
            }
        }
    }

    //this function decides what the dice will land on
    private fun diceRoll(image: ImageView) {
        //store the text view for the dice result in a variable
        val diceResult = findViewById<TextView>(R.id.diceResult)
        //generate a random number between 1 and 6 to decide what the dice lands on
        when ((1..6).random()) {
            //if number is 1
            1 -> {
                //set the image of the dice image to dice side 1
                image.setImageResource(R.drawable.dice1)
                //change the result text to say what side it landed on
                diceResult.setText("1!")
            }
            2 -> {
                //set image to dice side 2
                image.setImageResource(R.drawable.dice2)
                diceResult.setText("2!")
            }
            3 -> {
                //dice side 3
                image.setImageResource(R.drawable.dice3)
                diceResult.setText("3!")
            }
            4 -> {
                //dice side 4
                image.setImageResource(R.drawable.dice4)
                diceResult.setText("4!")
            }
            5 -> {
                //dice side 5
                image.setImageResource(R.drawable.dice5)
                diceResult.setText("5!")
            }
            else -> {
                //dice side 6
                image.setImageResource(R.drawable.dice6)
                diceResult.setText("6!")
            }
        }
    }
}
