package com.murlok.premierleaguefixtures.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun MatchListItem(match: String, onMatchClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .height(40.dp)
            .clickable { onMatchClick(match) }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "$match vs $match: $match")
        }
    }
}

@Preview
@Composable
fun MatchListScreen(onMatchClick: (String) -> Unit) {
    val dataset = List(50) { i -> (i + 1).toString() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            //.background(Color.LightGray)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Match List",
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier
            .fillMaxSize()) {
            items(dataset) { match ->
                MatchListItem(match = match, onMatchClick = onMatchClick)
            }
        }
    }
}