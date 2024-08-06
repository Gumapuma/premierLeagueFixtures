package com.murlok.premierleaguefixtures.view.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.murlok.premierleaguefixtures.R
import com.murlok.premierleaguefixtures.data.model.FootballMatch

@Composable
fun DetailsScreen(match: FootballMatch, onBack: () -> Unit) {
    val scrollState = rememberScrollState()

    BackHandler {
        onBack()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.detailsMatchNumber) + match.matchNumber,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = stringResource(id = R.string.detailsRoundNumber) + match.roundNumber,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
            }

            Text(
                text = stringResource(id = R.string.detailsDate) + match.dateUtc,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .align(Alignment.Start),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(id = R.string.detailsLocation) + match.location,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .align(Alignment.Start),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(id = R.string.detailsHomeTeam) + match.homeTeam,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .align(Alignment.Start),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(id = R.string.detailsAwayTeam) + match.awayTeam,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .align(Alignment.Start),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(id = R.string.detailsGroup) + match.group,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .align(Alignment.Start),
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.detailsHomeTeamScore) + match.homeTeamScore,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = stringResource(id = R.string.detailsAwayTeamScore) + match.awayTeamScore,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
            }

            Button(onClick = onBack) {
                Text(text = "Вернуться к списку матчей")
            }
        }
    }
}