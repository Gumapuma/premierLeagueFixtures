package com.murlok.premierleaguefixtures.ui.stateHolders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murlok.premierleaguefixtures.data.model.FootballMatch
import com.murlok.premierleaguefixtures.data.network.APIForRetrofit
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MatchListViewModel: ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://fixturedownload.com/feed/json/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val networkService = retrofit.create(APIForRetrofit::class.java)

    private val _matches = MutableLiveData<List<FootballMatch>>()
    val matches: LiveData<List<FootballMatch>> get() = _matches

    init {
        fetchMatches()
    }

    private fun fetchMatches() {
        viewModelScope.launch {
            try {
                val response = networkService.matches()
                _matches.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}