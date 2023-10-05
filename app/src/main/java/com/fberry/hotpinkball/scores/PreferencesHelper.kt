package com.fberry.hotpinkball.scores

interface PreferencesHelper {
    fun saveHighScore(score: Int)
    fun getHighScore(): Int
}