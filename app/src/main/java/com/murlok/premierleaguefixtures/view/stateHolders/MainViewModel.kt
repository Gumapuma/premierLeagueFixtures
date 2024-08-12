package com.murlok.premierleaguefixtures.view.stateHolders

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murlok.premierleaguefixtures.data.model.FootballMatch
import com.murlok.premierleaguefixtures.data.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class MainViewModel(private val repository: MainRepository) : ViewModel() {
    private val _matches = MutableStateFlow<List<FootballMatch>>(emptyList())
    val matches: StateFlow<List<FootballMatch>> get() = _matches

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> get() = _searchQuery

    val filteredMatches: StateFlow<List<FootballMatch>> = _searchQuery
        .combine(_matches) { query, matches ->
            Log.d("MainViewModel", "Filtering matches with query: $query")
            if (query.isEmpty()) {
                matches
            } else {
                matches.filter { it.homeTeam.contains(query, true) || it.awayTeam.contains(query, true) }
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

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

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        Log.d("MainViewModel", "Search query updated: $query")
    }
}