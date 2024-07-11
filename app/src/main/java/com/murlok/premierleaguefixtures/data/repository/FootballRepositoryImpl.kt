package com.murlok.premierleaguefixtures.data.repository
import com.murlok.premierleaguefixtures.data.model.FootballMatch
import com.murlok.premierleaguefixtures.data.network.APIForRetrofit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainRepositoryImpl: MainRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://fixturedownload.com/feed/json/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val networkApi = retrofit.create(APIForRetrofit::class.java)

    override fun getMatches(): Flow<List<FootballMatch>> = flow {
        val matches = networkApi.matches()
        emit(matches)
    }
}