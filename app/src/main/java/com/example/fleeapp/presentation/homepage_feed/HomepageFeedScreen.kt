package com.example.fleeapp.presentation.homepage_feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.fleeapp.domain.model.tracks.Track
import com.example.fleeapp.presentation.base_ui.FleeHeader
import com.example.fleeapp.presentation.base_ui.ListDisplayState
import com.example.fleeapp.presentation.homepage_feed.components.HorizontalTrackList
import com.example.fleeapp.presentation.homepage_feed.components.RowTrackItem


@Composable
fun HomepageFeedScreen(
    navController: NavController,
    viewModel: HomepageFeedViewModel = hiltViewModel()
) {
    val trackMap = mapOf(
        "Featured" to viewModel.featuredTracks.value,
        "Popular weekly" to viewModel.popularTracks.value,
        "Acoustic corner" to viewModel.acousticOnlyTracks.value
    )

    HomepageFeedBody(trackMap = trackMap)
}

@Composable
fun HomepageFeedBody(
    trackMap: Map<String, ListDisplayState<Track>>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        trackMap.forEach { trackList ->
            HorizontalTrackList(title = trackList.key, tracks = trackList.value)
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}