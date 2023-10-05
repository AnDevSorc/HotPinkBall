package com.fberry.hotpinkball.screens.choose

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.fberry.hotpinkball.R
import com.fberry.hotpinkball.screens.action.ActionActivity
import com.fberry.hotpinkball.screens.scores.ScoresActivity

class ChooseActivity : AppCompatActivity() {

    private lateinit var playGame: TextView
    private lateinit var setting: TextView
    private lateinit var exit: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)
        initView()
        listeners()
    }

    private fun initView() {
        playGame = findViewById(R.id.play_game)
        setting = findViewById(R.id.choose_settings)
        exit = findViewById(R.id.exit)
    }

    private fun listeners(){
        playGame.setOnClickListener {
            val intent = Intent(this@ChooseActivity, ActionActivity::class.java)
            startActivity(intent)
        }
        setting.setOnClickListener {
            val intent = Intent(this@ChooseActivity, ScoresActivity::class.java)
            startActivity(intent)
            finish()
        }
        exit.setOnClickListener { finishAffinity() }
    }
}