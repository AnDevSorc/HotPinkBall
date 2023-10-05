package com.fberry.hotpinkball.screens.scores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fberry.hotpinkball.scores.PreferencesHelper

class ScoresViewModel(private val prefs: PreferencesHelper): ViewModel() {
    private val _highScore = MutableLiveData(prefs.getHighScore())
    val highScore: LiveData<Int> get() = _highScore
}