package com.example.randomiserutilityapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.slider.Slider

class PasswordGenerator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_generator)
        //store the navigation bar as a variable
        val bottomNav = findViewById<BottomNavigationView>(R.id.navbar)

        //set listener for when an item is selected on the navbar
        bottomNav.setOnNavigationItemSelectedListener { item ->
            //check which item was pressed by the id
            when (item.itemId) {
                //if the coin flip button was pressed
                R.id.coinFlipBtn -> {
                    //create a new intent to start the CoinFlip activity
                    val intent = Intent(this, CoinFlip::class.java)
                    //start activity
                    startActivity(intent)
                }
                //if dice button was pressed
                R.id.diceRollMenuBtn -> {
                    //start dice activity
                    val intent = Intent(this, DiceRoll::class.java)
                    startActivity(intent)
                }
                //if password button was pressed
                R.id.pwMenuBtn -> {
                    //start password generator activity
                    val intent = Intent(this, PasswordGenerator::class.java)
                    startActivity(intent)
                }
            }
            true
        }
        //store the generate password button as a variable
        val genBtn = findViewById<Button>(R.id.generateBtn)
        //set the on click listener
        genBtn.setOnClickListener {
            //store the text view for the password as a variable
            val passText = findViewById<TextView>(R.id.passwordText)
            //call the function to generate a password and set it to the text view
            passText.setText(passwordGenerate())
            //if it was the first time generating a password change the button text to re-generate
            if(genBtn.getText() == "Generate") {
                genBtn.setText("Re-Generate")
                //change font size to fit button
                genBtn.setTextSize(16f)
            }
        }
        //store the copy button as a variable
        val copyBtn = findViewById<Button>(R.id.copyBtn)
        //set the on click listener
        copyBtn.setOnClickListener {
            //store the password result text view as a variable
            val pwordView = findViewById<TextView>(R.id.passwordText)
            //check if the textView is not empty
            if(pwordView.text.isNotEmpty()) {
                //if not empty then call the copy function and pass the text
                copyPassword(pwordView.text)
            }
        }
    }
    //this function copies the generated password to the user's clipboard
    private fun copyPassword(passwordText: CharSequence) {
        //create the clipboard
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        //create a clip and add the text to it
        val clip = ClipData.newPlainText("copy text", passwordText)
        //add the clip to the clipboard
        clipboard.setPrimaryClip(clip)
        //display a pop up saying the password is copied
        Toast.makeText(this, "Password Copied", Toast.LENGTH_LONG).show()
    }
    //this function returns a randomly generated password
    private fun passwordGenerate(): String {
        //store variables of the checkboxes
        val numCheck = findViewById<CheckBox>(R.id.numberCheck)
        val capCheck = findViewById<CheckBox>(R.id.capitalCheck)
        val specialCheck = findViewById<CheckBox>(R.id.specialCheck)
        //store the value entered in the password length slider
        val passLength = findViewById<Slider>(R.id.passLength).value.toInt()
        //create an object of the Password class with the length of password
        var password = Password(passLength)
        //if all three boxes are checked
        if (numCheck.isChecked && capCheck.isChecked && specialCheck.isChecked) {
            //return a password containing lower case and upper case, numbers and special chars
            return (password.generatePassword(7))
        } else if (numCheck.isChecked && capCheck.isChecked) {
            //return a password containing lower case, upper case and numbers
            return (password.generatePassword(4))
        } else if (numCheck.isChecked && specialCheck.isChecked) {
            //return password with numbers and special chars
            return (password.generatePassword(5))
        } else if (capCheck.isChecked && specialCheck.isChecked) {
            //return password with upper case and special chars
            return (password.generatePassword(6))
        } else if (numCheck.isChecked) {
            //return password with numbers
            return (password.generatePassword(1))
        } else if (capCheck.isChecked) {
            //return password with upper case chars
            return (password.generatePassword(2))
        } else if (specialCheck.isChecked) {
            //return password with special chars
            return (password.generatePassword(3))
        } else {
            //return password with just lower case chars
            return (password.generatePassword(0))
        }
    }
}
//this class represents a password that is generated
class Password(private val length: Int) {
    //initialise arrays of chars to be used in the passwords
    var chars = arrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
    'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
    'w', 'x', 'y', 'z')
    var capitalChars = arrayOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
    'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
    'W', 'X', 'Y', 'Z')
    var specialChars = arrayOf('!', '@', '#', '$', '&', '*', '(', ')', '+', '-', '_')
    //this function returns a char from the lower case char array
    fun getRandomChar(): Char {
        //random number between 0 and 25
        var randomInt = (0..25).random()
        //return the a char from the array using the index of the random number
        return chars[randomInt]
    }
    //return a random capital letter
    fun getRandomCapital(): Char {
        var randomInt = (0..25).random()
        return capitalChars[randomInt]
    }
    //return a random int between 0 and 9
    fun getRandomNum(): Int {
        return (0..9).random()
    }
    //return a random special char
    fun getRandomSpecial(): Char {
        var randomInt = (0..10).random()
        return specialChars[randomInt]
    }
    //function to generate a random password
    fun generatePassword(passwordType: Int): String {
        //initialise the password string
        var passwordString = ""
        //loop that generates each character in the password, loop is set to length of password
        for(i in 1..length) {
            //check what the type of password is to decide what chars are added to the string
            when (passwordType) {
                //if password type is 0 add 12 random lower case chars to the string
                0 -> passwordString += getRandomChar()
                1 -> {
                    //if type is 1 generate a random number, either 1 or 2
                    when ((1..2).random()) {
                        //if its 1 add a char
                        1 -> passwordString += getRandomChar()
                        //if its 2 add a number
                        else -> passwordString += getRandomNum()
                    }
                }
                2 -> {
                    when ((1..2).random()) {
                        //if 1 add char
                        1 -> passwordString += getRandomChar()
                        //if 2 add capital
                        else -> passwordString += getRandomCapital()
                    }
                }
                3 -> {
                    when ((1..2).random()) {
                        //add lower case char
                        1 -> passwordString += getRandomChar()
                        //add special char
                        else -> passwordString += getRandomSpecial()
                    }
                }
                4 -> {
                    //generate number between 1 and 3
                    when ((1..3).random()) {
                        //add char
                        1 -> passwordString += getRandomChar()
                        //add num
                        2 -> passwordString += getRandomNum()
                        //add captial
                        else -> passwordString += getRandomCapital()
                    }
                }
                5 -> {
                    when ((1..3).random()) {
                        //add char
                        1 -> passwordString += getRandomChar()
                        //add num
                        2 -> passwordString += getRandomNum()
                        //add special
                        else -> passwordString += getRandomSpecial()
                    }
                }
                6 -> {
                    when ((1..3).random()) {
                        //char
                        1 -> passwordString += getRandomChar()
                        //capital
                        2 -> passwordString += getRandomCapital()
                        //special
                        else -> passwordString += getRandomSpecial()
                    }
                }
                //else add all
                else -> {
                    //generate random number between 1 and 4
                    when ((1..4).random()) {
                        //char
                        1 -> passwordString += getRandomChar()
                        //num
                        2 -> passwordString += getRandomNum()
                        //capital
                        3 -> passwordString += getRandomCapital()
                        //special
                        4 -> passwordString += getRandomSpecial()
                    }
                }
            }
        }
        //return the password string
        return passwordString
    }
}