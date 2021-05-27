package com.example.xmlanimation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
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
            rotateHelloToShowWorld(it)
        }


        binding.tvWorld.setOnClickListener {
            Toast.makeText(this, "World clicked", Toast.LENGTH_SHORT).show()
        }


    }

    private fun rotateHelloToShowWorld(view: View) {
        val rotate = if(!isBackShown){
            ObjectAnimator.ofFloat(view, "rotation", 0f, -75f).apply {
                duration = 1000L
            }
        }else{
            ObjectAnimator.ofFloat(view, "rotation", -75f, 0f).apply {
                duration = 1000L
            }
        }
        val pivotX = ObjectAnimator.ofFloat(view, "pivotX", 0f)
        val pivotY = ObjectAnimator.ofFloat(view, "pivotY", view.height.toFloat())

        isBackShown = !isBackShown

        AnimatorSet().apply {
            playTogether(rotate, pivotX, pivotY)
            start()
        }
    }
}