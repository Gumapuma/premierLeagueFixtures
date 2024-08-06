package com.murlok.premierleaguefixtures.data.model

import com.google.gson.annotations.SerializedName
import com.murlok.premierleaguefixtures.data.room.dao.FootballMatchEntity


data class FootballMatch(
    @SerializedName("MatchNumber")
    val matchNumber: Int,
    @SerializedName("RoundNumber")
    val roundNumber: Int,
    @SerializedName("DateUtc")
    val dateUtc: String,
    @SerializedName("Location")
    val location: String,
    @SerializedName("HomeTeam")
    val homeTeam: String,
    @SerializedName("AwayTeam")
    val awayTeam: String,
    @SerializedName("Group")
    val group: String?,
    @SerializedName("HomeTeamScore")
    val homeTeamScore: Int,
    @SerializedName("AwayTeamScore")
    val awayTeamScore: Int
) {
    companion object {
        fun FootballMatch.toFootballMatchEntity(): FootballMatchEntity = FootballMatchEntity(
            matchNumber = this.matchNumber,
            roundNumber = this.roundNumber,
            dateUtc = this.dateUtc,
            location = this.location,
            homeTeam = this.homeTeam,
            awayTeam = this.awayTeam,
            group = this.group,
            homeTeamScore = this.homeTeamScore,
            awayTeamScore = this.awayTeamScore
        )
    }

}