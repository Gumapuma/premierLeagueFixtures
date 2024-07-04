package com.murlok.premierleaguefixtures.ui.main

import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.murlok.premierleaguefixtures.R

@Preview
@Composable
fun DetailsScreen(match: String, onBack: () -> Unit) {
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
                    text = stringResource(id = R.string.detailsMatchNumber),
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = stringResource(id = R.string.detailsRoundNumber),
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
            }

            Text(
                text = stringResource(id = R.string.detailsDate),
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(id = R.string.detailsLocation),
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(id = R.string.detailsHomeTeam),
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(id = R.string.detailsAwayTeam),
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(id = R.string.detailsGroup),
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.detailsHomeTeamScore),
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = stringResource(id = R.string.detailsAwayTeamScore),
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