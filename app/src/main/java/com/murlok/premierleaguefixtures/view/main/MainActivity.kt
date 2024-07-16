package com.murlok.premierleaguefixtures.view.main

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.murlok.premierleaguefixtures.data.model.FootballMatch
import com.murlok.premierleaguefixtures.data.network.APIForRetrofit
import com.murlok.premierleaguefixtures.data.repository.MainRepositoryImpl
import com.murlok.premierleaguefixtures.data.room.MatchDatabase
import com.murlok.premierleaguefixtures.view.stateHolders.MainViewModel
import com.murlok.premierleaguefixtures.view.theme.PremiereLeagueFixturesTheme
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PremiereLeagueFixturesTheme {
                MainScreen(context = applicationContext)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel(), context: Context) {
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