package com.example.xmlanimation

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.xmlanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var isBackShown = false

    private val frontRotator by lazy {
        AnimatorInflater.loadAnimator(this, R.animator.anim_flip_card_front)
    }

    private val backRotator by lazy {
        AnimatorInflater.loadAnimator(this, R.animator.anim_flip_card_back)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCameraDistanceForViews()

        binding.tvHello.setOnClickListener {
            flipCard()
        }
        binding.tvWorld.setOnClickListener {
            flipCard()
        }
    }

    private fun flipCard() {

        if(!isBackShown) {

            frontRotator.setTarget(binding.tvHello)
            backRotator.setTarget(binding.tvWorld)
            AnimatorSet().apply {
                playTogether(frontRotator, backRotator)
                start()
            }

        }else {

            frontRotator.setTarget(binding.tvWorld)
            backRotator.setTarget(binding.tvHello)
            AnimatorSet().apply {
                playTogether(frontRotator, backRotator)
                start()
            }

        }

        isBackShown = !isBackShown
    }

    private fun setupCameraDistanceForViews() {
        val scale = this.resources.displayMetrics.density
        val distance = 5000
        binding.tvHello.cameraDistance = scale * distance
        binding.tvWorld.cameraDistance = scale * distance
    }

}