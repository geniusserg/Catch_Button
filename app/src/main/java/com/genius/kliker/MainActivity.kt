package com.genius.kliker

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.widget.Button
import androidx.core.view.ViewCompat

var active : Boolean = false

class MainActivity : AppCompatActivity() {
    var button : Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        button?.x = 0F
        button?.y = 0F
        button?.setOnClickListener {
            active = false
            val intent = Intent(this@MainActivity, WonActivity::class.java)
            startActivity(intent)
            button?.clearAnimation()
        }
        Thread {
            animateManager()
        }.start()
    }

    private fun animateManager() {
        active = true
        while (active) {
            runOnUiThread {
                ViewCompat.animate(findViewById(R.id.button))
                        .translationX((1000..5000).random() / 10F)
                        .translationY((1000..8000).random() / 10F)
                        .setDuration(200)
            }
            Thread.sleep(250)
            button?.setBackgroundColor((Color.BLACK..Color.WHITE).random())
        }
    }

    override fun onResume() {
        super.onResume()
        Thread {animateManager()}.start()
    }

}

class WonActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_won)
    }
}