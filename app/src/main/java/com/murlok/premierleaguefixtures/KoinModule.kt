package com.murlok.premierleaguefixtures

import com.murlok.premierleaguefixtures.data.network.APIForRetrofit
import com.murlok.premierleaguefixtures.data.repository.MainRepository
import com.murlok.premierleaguefixtures.data.repository.MainRepositoryImpl
import com.murlok.premierleaguefixtures.data.room.MatchDatabase
import com.murlok.premierleaguefixtures.view.stateHolders.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://fixturedownload.com/feed/json/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIForRetrofit::class.java)
    }

    single { MatchDatabase.getDatabase(androidContext()) }

    single<MainRepository> {
        MainRepositoryImpl(get(), get())
    }

    viewModel { MainViewModel(get()) }
}