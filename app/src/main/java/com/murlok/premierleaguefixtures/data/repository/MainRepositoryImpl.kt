package com.murlok.premierleaguefixtures.data.repository
import com.murlok.premierleaguefixtures.data.model.FootballMatch
import com.murlok.premierleaguefixtures.data.model.FootballMatch.Companion.toFootballMatchEntity
import com.murlok.premierleaguefixtures.data.network.APIForRetrofit
import com.murlok.premierleaguefixtures.data.room.MatchDatabase
import com.murlok.premierleaguefixtures.data.room.dao.FootballMatchEntity
import com.murlok.premierleaguefixtures.data.room.dao.FootballMatchEntity.Companion.toFootballMatch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class MainRepositoryImpl(
    private val networkApi: APIForRetrofit,
    private val database: MatchDatabase?
) : MainRepository {

    override suspend fun getMatchesFromLocal(): Flow<List<FootballMatch>> = flow {
        val matchEntities = withContext(Dispatchers.IO) {
            database!!.matchListDao().getAllMatches()
        }
        val matches: List<FootballMatch> = matchEntities.map { it.toFootballMatch() }
        emit(matches)
    }

    override suspend fun updateLocalMatches() {
        val matches = withContext(Dispatchers.IO) {
            networkApi.matches()
        }
        val matchesEntities: List<FootballMatchEntity> = matches.map { it.toFootballMatchEntity() }
        withContext(Dispatchers.IO){
            matchesEntities.forEach { database!!.matchListDao().addMatch(it) }
        }
    }
}