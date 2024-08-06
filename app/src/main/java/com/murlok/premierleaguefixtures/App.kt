package com.murlok.premierleaguefixtures

import android.app.Application
import android.util.Log
import com.murlok.premierleaguefixtures.data.room.MatchDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}