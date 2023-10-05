package com.fberry.hotpinkball.application

import android.app.Application
import com.fberry.hotpinkball.injection.diModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HotPinkBallApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@HotPinkBallApplication)
            modules(diModule)
        }
    }
}