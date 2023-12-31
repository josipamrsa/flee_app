package com.example.fleeapp.presentation.homepage_feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fleeapp.domain.model.tracks.Track
import com.example.fleeapp.presentation.common_ui.ListDisplayState
import com.example.fleeapp.presentation.common_ui.theme.flee_main.FleeMainTheme
import com.example.fleeapp.presentation.homepage_feed.components.HorizontalTrackList
import com.example.fleeapp.presentation.homepage_feed.states.PreviewTrackState


@Composable
fun HomepageFeedScreen(
    navController: NavController,
    viewModel: HomepageFeedViewModel = hiltViewModel(),
) {
    val nowPlayingTrack by viewModel.isNowPlayingTrack.collectAsState()
    val filterableTitle by viewModel.filterableTitle.collectAsState()

    val trackMap = mapOf(
        "Featured" to viewModel.featuredTracks.value,
        "Popular weekly" to viewModel.popularTracks.value,
        "Acoustic corner" to viewModel.acousticOnlyTracks.value
    )

    HomepageFeedBody(
        trackMap = trackMap,
        trackPlaying = nowPlayingTrack,
        filterableTitle = filterableTitle,
        onFilterableClicked = { frequency ->
            viewModel.getPopularTracks(frequency)
        },
        onTrackClick = { },
        onTrackDoubleClick = { trackState ->
            viewModel.onSetNowPlayingTrack(trackState).also {
                viewModel.playTenSecondPreview(trackState)
            }
        }
    )
}

@Composable
fun HomepageFeedBody(
    trackMap: Map<String, ListDisplayState<Track>>,
    trackPlaying: PreviewTrackState<Track>,
    filterableTitle: String,
    onFilterableClicked: (Map.Entry<String, String>) -> Unit,
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
                trackPlaying = trackPlaying,
                filterableTitle = filterableTitle,
                onFilterableClicked = onFilterableClicked,
                onTrackClick = onTrackClick,
                onTrackDoubleClick = { onTrackDoubleClick(it) }
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}