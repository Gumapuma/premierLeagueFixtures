package com.murlok.premierleaguefixtures.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.murlok.premierleaguefixtures.data.room.dao.FootballMatchEntity
import com.murlok.premierleaguefixtures.data.room.dao.MatchListDao

@Database(entities = [FootballMatchEntity::class], version = 1, exportSchema = true)
abstract class MatchDatabase : RoomDatabase() {
    abstract fun matchListDao(): MatchListDao

    companion object {
        @Volatile
        private var INSTANCE: MatchDatabase? = null

        fun getDatabase(context: Context): MatchDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MatchDatabase::class.java,
                    "match_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}

