package com.murlok.premierleaguefixtures.data.repository

import com.murlok.premierleaguefixtures.data.model.FootballMatch
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getMatchesFromLocal(): Flow<List<FootballMatch>>
    suspend fun updateLocalMatches(): Unit
}