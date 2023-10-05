package com.fberry.hotpinkball.screens.action

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fberry.hotpinkball.scores.PreferencesHelper

class ActionViewModel(private val prefs: PreferencesHelper) : ViewModel() {

    private val _count = MutableLiveData<Int>(10)
    val count: LiveData<Int> get() = _count

    private val _score = MutableLiveData<Int>(100)
    val score: LiveData<Int> get() = _score

    private val _highScore = MutableLiveData<Int>(prefs.getHighScore())
    val highScore: LiveData<Int> get() = _highScore

    fun reduceCount() {
        _count.value = _count.value?.minus(1)
        if (_count.value == 0) {
            checkAndSaveHighScore()
        }
    }

    fun updateScore(multiplier: Float) {
        val currentScore = _score.value ?: 0
        _score.value = (currentScore * multiplier).toInt()
    }

    fun hasAttemptsLeft(): Boolean {
        return (_count.value ?: 0) > 0
    }

    fun resetScores() {
        _count.value = 10
        _score.value = 0
    }

    private fun checkAndSaveHighScore() {
        val currentScore = _score.value ?: 0
        val currentHighScore = _highScore.value ?: 0

        if (currentScore > currentHighScore) {
            _highScore.value = currentScore
            prefs.saveHighScore(currentScore)
        }
    }
}

