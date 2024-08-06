package com.murlok.premierleaguefixtures.data.room.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.murlok.premierleaguefixtures.data.model.FootballMatch
import com.murlok.premierleaguefixtures.data.room.dao.FootballMatchEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class FootballMatchEntity(
    @PrimaryKey @ColumnInfo("MatchNumber")
    val matchNumber: Int,
    @ColumnInfo("RoundNumber")
    val roundNumber: Int,
    @ColumnInfo("DateUtc")
    val dateUtc: String,
    @ColumnInfo("Location")
    val location: String,
    @ColumnInfo("HomeTeam")
    val homeTeam: String,
    @ColumnInfo("AwayTeam")
    val awayTeam: String,
    @ColumnInfo("Group")
    val group: String?,
    @ColumnInfo("HomeTeamScore")
    val homeTeamScore: Int,
    @ColumnInfo("AwayTeamScore")
    val awayTeamScore: Int
) {
    companion object {
        const val TABLE_NAME = "match_list_table"

        fun FootballMatchEntity.toFootballMatch(): FootballMatch = FootballMatch(
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