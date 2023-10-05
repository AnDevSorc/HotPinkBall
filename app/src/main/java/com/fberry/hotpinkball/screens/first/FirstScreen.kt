package com.fberry.hotpinkball.screens.first

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fberry.hotpinkball.R
import com.fberry.hotpinkball.screens.choose.ChooseActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FirstScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        load()
        animation()
    }

    private fun load() {
        lifecycleScope.launch {
            delay(2000)
            val i = Intent(this@FirstScreen, ChooseActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun animation(){
        val ball = findViewById<ImageView>(R.id.ball)

        val leftToRight = AnimatorInflater.loadAnimator(this, R.animator.left_to_right) as ObjectAnimator
        leftToRight.target = ball

        val rightToLeft = AnimatorInflater.loadAnimator(this, R.animator.right_to_left) as ObjectAnimator
        rightToLeft.target = ball

        val set = AnimatorSet()
        set.playSequentially(leftToRight, rightToLeft)
        set.addListener(object : Animator.AnimatorListener {

            override fun onAnimationStart(p0: Animator) {}

            override fun onAnimationEnd(p0: Animator) { set.start() }

            override fun onAnimationCancel(p0: Animator) {}

            override fun onAnimationRepeat(p0: Animator) {}
        })
        set.start()
    }
}