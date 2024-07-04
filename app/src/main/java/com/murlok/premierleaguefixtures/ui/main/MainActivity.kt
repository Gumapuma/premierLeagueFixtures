package com.murlok.premierleaguefixtures.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.murlok.premierleaguefixtures.ui.theme.PremiereLeagueFixturesTheme

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
fun MainScreen() {
    var selectedMatch by remember { mutableStateOf<String?>(null) }

    if (selectedMatch == null) {
        MatchListScreen { match ->
            selectedMatch = match
        }
    } else {
        DetailsScreen(match = selectedMatch!!, onBack = { selectedMatch = null })
    }
}