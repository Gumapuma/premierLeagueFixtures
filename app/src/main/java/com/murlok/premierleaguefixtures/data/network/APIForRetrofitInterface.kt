package com.murlok.premierleaguefixtures.data.network

import com.murlok.premierleaguefixtures.data.model.FootballMatch
import retrofit2.http.GET

interface APIForRetrofit {
    @GET("epl-2024")
    suspend fun matches(): List<FootballMatch>
}