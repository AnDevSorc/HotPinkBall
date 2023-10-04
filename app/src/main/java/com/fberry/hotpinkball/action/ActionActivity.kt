package com.fberry.hotpinkball.action

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fberry.hotpinkball.R

class ActionActivity : AppCompatActivity() {
    private lateinit var viewModel: ActionViewModel
    private lateinit var startButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[ActionViewModel::class.java]
        val plinkoGridView = findViewById<ActionBoxImageView>(R.id.plinkoGridImageView)
        startButton = findViewById<TextView>(R.id.startButton)
        val scoresTextView = findViewById<TextView>(R.id.total_score)
        val repeatButton = findViewById<TextView>(R.id.repeatButton)
        viewModel.score.observe(this) { score ->
            scoresTextView.text = "Scores: $score"
        }
        viewModel.count.observe(this) { count ->
            if (count == 0) {
                startButton.visibility = View.GONE
                Toast.makeText(this, "Game over with ${scoresTextView.text}", Toast.LENGTH_SHORT)
                    .show()
                repeatButton.visibility = View.VISIBLE
            } else {
                repeatButton.visibility = View.GONE
                startButton.visibility = View.VISIBLE
            }
        }

        plinkoGridView.onBallInBasketListener = { basketIndex ->
            onBallInBasket(basketIndex)
        }

        startButton.setOnClickListener {
            if (viewModel.hasAttemptsLeft()) {
                plinkoGridView.resetBallPosition()
                plinkoGridView.moveBall()
                startButton.visibility = View.GONE
            }
        }

        repeatButton.setOnClickListener {
            viewModel.resetScores()
            plinkoGridView.resetBallPosition()
        }
    }

    private fun onBallInBasket(basketIndex: Int) {
        val basketMultipliers = listOf(5f, 3f, 1.5f, 0.5f, 1.5f, 3f, 5f)
        viewModel.updateScore(basketMultipliers[basketIndex])
        viewModel.reduceCount()
        if (viewModel.count.value != 0) {
            startButton.visibility = View.VISIBLE
        }
    }
}
