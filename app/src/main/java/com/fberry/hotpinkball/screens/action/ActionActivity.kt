package com.fberry.hotpinkball.screens.action

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fberry.hotpinkball.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActionActivity : AppCompatActivity() {
    private val viewModel: ActionViewModel by viewModel()
    private lateinit var goBtn: TextView
    private lateinit var scores: TextView
    private lateinit var reset: TextView
    private lateinit var box: Desc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action)
        initView()
        listeners()
        observation()
    }

    private fun initView() {
        box = findViewById(R.id.box)
        goBtn = findViewById(R.id.startGo)
        scores = findViewById(R.id.score)
        reset = findViewById(R.id.repeatGo)
    }

    private fun observation() {
        viewModel.score.observe(this) { score ->
            scores.text = "Scores: $score"
        }
        viewModel.count.observe(this) { count ->
            if (count == 0) {
                goBtn.visibility = View.GONE
                Toast.makeText(this, "Game over with ${scores.text}", Toast.LENGTH_SHORT)
                    .show()
                reset.visibility = View.VISIBLE
            } else {
                reset.visibility = View.GONE
                goBtn.visibility = View.VISIBLE
            }
        }
    }

    private fun listeners() {
        box.onBallInBasketListener = { basketIndex ->
            ballFault(basketIndex)
        }

        goBtn.setOnClickListener {
            if (viewModel.hasAttemptsLeft()) {
                box.resetBallPosition()
                box.moveBall()
                goBtn.visibility = View.GONE
            }
        }

        reset.setOnClickListener {
            viewModel.resetScores()
            box.resetBallPosition()
        }
    }

    private fun ballFault(basketIndex: Int) {
        val basketMultipliers = listOf(5f, 3f, 1.5f, 0.5f, 1.5f, 3f, 5f)
        viewModel.updateScore(basketMultipliers[basketIndex])
        viewModel.reduceCount()
        if (viewModel.count.value != 0) {
            goBtn.visibility = View.VISIBLE
        }
    }
}

