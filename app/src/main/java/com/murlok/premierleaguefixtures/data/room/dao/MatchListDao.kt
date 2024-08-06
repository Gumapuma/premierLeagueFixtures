package com.murlok.premierleaguefixtures.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchListDao {
    @Query("SELECT * FROM ${FootballMatchEntity.TABLE_NAME}")
    fun getAllMatches(): List<FootballMatchEntity>

    @Insert(entity = FootballMatchEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun addMatch(entity: FootballMatchEntity)
}