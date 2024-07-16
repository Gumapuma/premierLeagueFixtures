package com.murlok.premierleaguefixtures

import android.app.Application
import android.util.Log
import com.murlok.premierleaguefixtures.data.room.MatchDatabase

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        Log.d("RoomCustomLog", MatchDatabase.createDatabase(this).toString())
    }
}