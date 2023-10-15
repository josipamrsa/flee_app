package com.example.fleeapp.presentation.homepage_feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fleeapp.presentation.homepage_feed.components.PopularTrackItem


@Composable
fun HomepageFeedScreen(
    navController: NavController,
    viewModel: HomepageFeedViewModel = hiltViewModel()
) {
    val popularTracks = viewModel.popularTracks.value

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(15.dp)
        ) {
            Text(
                text = "Flee",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Start
            )
        }

        Text(
            text = "Popular weekly",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(
                top = 30.dp,
                start = 20.dp
            )
        )

        LazyRow(modifier = Modifier.fillMaxSize()) {
            items(popularTracks.data) { track ->
                PopularTrackItem(track = track)
            }
        }

        if (popularTracks.error.isNotBlank()) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = popularTracks.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
        }

        if (popularTracks.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}