package com.murlok.premierleaguefixtures.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.murlok.premierleaguefixtures.data.room.dao.FootballMatchEntity
import com.murlok.premierleaguefixtures.data.room.dao.MatchListDao

@Database(entities = [
    FootballMatchEntity::class
], version = 1, exportSchema = true)
abstract class MatchDatabase: RoomDatabase() {
    abstract fun matchListDao(): MatchListDao

    companion object {
        private lateinit var INSTANCE: MatchDatabase

        fun createDatabase(context: Context): Boolean
        {
            if(!::INSTANCE.isInitialized)
            {
                INSTANCE=
                    Room.databaseBuilder(context, MatchDatabase::class.java,"match_database")
                        .fallbackToDestructiveMigration().build()
                return true
            }
            return false
        }

        fun getDatabase(): MatchDatabase = INSTANCE
    }
}

