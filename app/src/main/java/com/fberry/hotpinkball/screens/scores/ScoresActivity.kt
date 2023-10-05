package com.fberry.hotpinkball.screens.scores

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.fberry.hotpinkball.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScoresActivity : AppCompatActivity() {
    private val viewModel: ScoresViewModel by viewModel()
    private lateinit var scores: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)
        initViews()
        initObs()
    }

    private fun initViews() {
        scores = findViewById(R.id.score)
    }

    private fun initObs() {
        viewModel.highScore.observe(this) { highScore ->
            scores.text = "$highScore"
        }
    }

}