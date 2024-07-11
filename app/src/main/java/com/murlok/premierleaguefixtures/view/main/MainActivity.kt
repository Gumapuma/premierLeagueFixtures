package com.murlok.premierleaguefixtures.view.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.murlok.premierleaguefixtures.data.model.FootballMatch
import com.murlok.premierleaguefixtures.view.stateHolders.MainViewModel
import com.murlok.premierleaguefixtures.view.theme.PremiereLeagueFixturesTheme

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            PremiereLeagueFixturesTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    var selectedMatch by remember { mutableStateOf<FootballMatch?>(null) }
    val matches by viewModel.matches.collectAsState()

    if (selectedMatch == null) {
        MatchListScreen(matches) { match ->
            selectedMatch = match
        }
    } else {
        DetailsScreen(match = selectedMatch!!, onBack = { selectedMatch = null })
    }
}