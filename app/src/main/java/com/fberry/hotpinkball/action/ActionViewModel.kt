package com.fberry.hotpinkball.action

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActionViewModel : ViewModel() {
    private val _count = MutableLiveData<Int>(10)
    val count: LiveData<Int> get() = _count

    private val _score = MutableLiveData<Int>(100)
    val score: LiveData<Int> get() = _score

    fun reduceCount() {
        _count.value = _count.value?.minus(1)
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
        _score.value = 100
    }
}