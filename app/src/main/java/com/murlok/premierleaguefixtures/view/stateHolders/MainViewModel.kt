package com.murlok.premierleaguefixtures.view.stateHolders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murlok.premierleaguefixtures.data.model.FootballMatch
import com.murlok.premierleaguefixtures.data.repository.MainRepository
import com.murlok.premierleaguefixtures.data.repository.MainRepositoryImpl
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(repository: MainRepository = MainRepositoryImpl()): ViewModel() {
    private val _matches = repository.getMatches()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList<FootballMatch>())

    val matches: StateFlow<List<FootballMatch>> = _matches
}