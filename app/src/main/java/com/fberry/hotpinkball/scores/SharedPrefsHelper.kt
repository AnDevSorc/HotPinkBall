package com.fberry.hotpinkball.scores

import android.content.Context
import android.content.SharedPreferences


class SharedPrefsHelper(private val context: Context) : PreferencesHelper {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("default", Context.MODE_PRIVATE)

    override fun saveHighScore(score: Int) {
        sharedPreferences.edit().putInt("HIGH_SCORE", score).apply()
    }

    override fun getHighScore(): Int {
        return sharedPreferences.getInt("HIGH_SCORE", 0)
    }
}