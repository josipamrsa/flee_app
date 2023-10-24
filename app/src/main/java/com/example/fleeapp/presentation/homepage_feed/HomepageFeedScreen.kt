package com.example.fleeapp.presentation.homepage_feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fleeapp.domain.model.tracks.Track
import com.example.fleeapp.presentation.base_ui.ListDisplayState
import com.example.fleeapp.presentation.base_ui.theme.flee_main.FleeMainTheme
import com.example.fleeapp.presentation.homepage_feed.components.HorizontalTrackList


@Composable
fun HomepageFeedScreen(
    navController: NavController,
    viewModel: HomepageFeedViewModel = hiltViewModel(),
) {
    val trackMap = mapOf(
        "Featured" to viewModel.featuredTracks.value,
        "Popular weekly" to viewModel.popularTracks.value,
        "Acoustic corner" to viewModel.acousticOnlyTracks.value
    )

    var isToStop: Boolean = false

    HomepageFeedBody(
        trackMap = trackMap,
        onTrackClick = { },
        onTrackDoubleClick = {
            viewModel.playOrStopAudio(it.audio, isToStop)
            isToStop = !isToStop
        }
    )
}

@Composable
fun HomepageFeedBody(
    trackMap: Map<String, ListDisplayState<Track>>,
    onTrackClick: () -> Unit,
    onTrackDoubleClick: (Track) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(FleeMainTheme.colors.backgroundPrimary)

    ) {

        trackMap.forEach { trackList ->
            HorizontalTrackList(
                title = trackList.key,
                tracks = trackList.value,
                onTrackClick = onTrackClick,
                onTrackDoubleClick = { onTrackDoubleClick(it) }
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}