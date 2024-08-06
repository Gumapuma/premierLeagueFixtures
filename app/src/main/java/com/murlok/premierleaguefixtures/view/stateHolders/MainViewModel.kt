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

class MainViewModel(private val repository: MainRepository) : ViewModel() {
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