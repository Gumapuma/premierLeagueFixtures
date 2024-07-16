package com.murlok.premierleaguefixtures.view.stateHolders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murlok.premierleaguefixtures.data.model.FootballMatch
import com.murlok.premierleaguefixtures.data.network.APIForRetrofit
import com.murlok.premierleaguefixtures.data.repository.MainRepository
import com.murlok.premierleaguefixtures.data.repository.MainRepositoryImpl
import com.murlok.premierleaguefixtures.data.room.MatchDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://fixturedownload.com/feed/json/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val networkApi: APIForRetrofit = retrofit.create(APIForRetrofit::class.java)

    private val database = MatchDatabase.getDatabase()

    private val repository: MainRepository = MainRepositoryImpl(networkApi, database)
    private val _matches = MutableStateFlow<List<FootballMatch>>(emptyList())
    val matches: StateFlow<List<FootballMatch>> get() = _matches

    init {
        viewModelScope.launch {
            fetchLocalMatches()
        }
    }

    private suspend fun fetchLocalMatches() {
        repository.getMatchesFromLocal().collect {
            _matches.value = it
        }
    }

    fun updateLocalMatches() {
        viewModelScope.launch {
            repository.updateLocalMatches()
            fetchLocalMatches()
        }
    }
}