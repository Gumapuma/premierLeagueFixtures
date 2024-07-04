package com.murlok.premierleaguefixtures.data.repository
import com.murlok.premierleaguefixtures.data.model.FootballMatch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FootballRepository {

    fun getMatches(): Flow<List<FootballMatch>> = flow {
        TODO()
        //val matches = api.getMatches()
        //emit(matches)
    }
}