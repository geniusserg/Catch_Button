package com.genius.kliker

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

var active : Boolean = false

class MainActivity : AppCompatActivity() {
    lateinit var button : Button
    var x:Float? = 0F
    var y:Float? = 0F
    val xmax = 10F
    val ymax = 10F
    var step = 0.01F
    var arg = 1F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        x = button.x
        y = button.y
        button.setOnClickListener {
            View.OnClickListener {
                active = false
                val intent = Intent(applicationContext, WonActivity::class.java)
                startActivity(intent)
            }
        }
        Thread{
            active = true
            while (active) {
                Thread.sleep(1000)
                runOnUiThread {
                    x?.let {button.setX(it)}
                    getYparam()?.let { button.setY(it) }
                    x?.plus(step)
                }
            }
        }.start()
    }
    fun getYparam() : Float? {
        if (x == xmax || x == 0F){
            step = -step
            button.setBackgroundColor((Color.BLACK..Color.WHITE).random())
        }
        if (y == ymax || y == 0F){
            arg = -arg
            button.setBackgroundColor((Color.BLACK..Color.WHITE).random())
        }
        y = y?.plus(arg*step)
        print(y)
        return y
    }
}

class WonActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_won)
        active = false
    }
}