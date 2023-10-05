package com.fberry.hotpinkball.injection

import com.fberry.hotpinkball.scores.PreferencesHelper
import com.fberry.hotpinkball.scores.SharedPrefsHelper
import com.fberry.hotpinkball.screens.action.ActionViewModel
import com.fberry.hotpinkball.screens.scores.ScoresViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val diModule = module {
    single<PreferencesHelper> { SharedPrefsHelper(androidContext()) }
    viewModel { ActionViewModel(get()) }
    viewModel { ScoresViewModel(get()) }
}