package com.murlok.premierleaguefixtures.view.main

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.murlok.premierleaguefixtures.data.model.FootballMatch
import com.murlok.premierleaguefixtures.view.stateHolders.MainViewModel
import com.murlok.premierleaguefixtures.view.theme.PremiereLeagueFixturesTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel: MainViewModel = getViewModel()

        setContent {
            PremiereLeagueFixturesTheme {
                MainScreen(mainViewModel, context = applicationContext)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel, context: Context) {
    var selectedMatch by remember { mutableStateOf<FootballMatch?>(null) }
    val matches by viewModel.matches.collectAsState()

    if(isOnline(context = context)) {
        viewModel.updateLocalMatches()
    }

    if (selectedMatch == null) {
        if (matches.isEmpty()) {
            CircularProgressIndicator()
        } else {
            MatchListScreen(matches) {match -> selectedMatch = match}
        }
    } else {
        DetailsScreen(match = selectedMatch!!, onBack = { selectedMatch = null })
    }
}

fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val network = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
    return when {
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
}