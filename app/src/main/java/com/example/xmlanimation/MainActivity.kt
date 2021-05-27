package com.example.xmlanimation

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import com.example.xmlanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var isBackShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvHello.setOnClickListener {
            translateHelloAboveWorld()
        }


        binding.tvWorld.setOnClickListener {
            Toast.makeText(this, "World clicked", Toast.LENGTH_SHORT).show()
        }


    }

    private fun translateHelloAboveWorld() {

        val anim = if(!isBackShown) {
            ObjectAnimator.ofFloat(binding.tvHello, "translationY", -(binding.tvHello.height.toFloat() + 10)).apply {
                duration = 1000
                interpolator = AccelerateDecelerateInterpolator()
            }
        }else {
            ObjectAnimator.ofFloat(binding.tvHello, "translationY", 0f).apply {
                duration = 1000
                interpolator = AccelerateDecelerateInterpolator()
            }
        }

        isBackShown = !isBackShown

        anim.start()

    }

}