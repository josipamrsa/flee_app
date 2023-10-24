package com.example.fleeapp.presentation.homepage_feed.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fleeapp.domain.model.tracks.Track
import com.example.fleeapp.presentation.base_ui.DataErrorHandler
import com.example.fleeapp.presentation.base_ui.DataLoaderDisplay
import com.example.fleeapp.presentation.base_ui.ListDisplayState
import com.example.fleeapp.presentation.base_ui.theme.flee_main.FleeMainTheme

@Composable
fun HorizontalTrackList(
    title: String,
    tracks: ListDisplayState<Track>,
    onTrackClick: (Track) -> Unit,
    onTrackDoubleClick: () -> Unit,
) {
    Text(
        text = title,
        style = FleeMainTheme.typography.h6,
        color = FleeMainTheme.colors.textAccentPrimary,
        modifier = Modifier.padding(
            top = 30.dp,
            start = 20.dp
        )
    )

    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(tracks.data) { track ->
            RowTrackItem(
                track = track,
                onTrackClick = {
                    onTrackClick(it)
                },
                onTrackDoubleClick = onTrackDoubleClick
            )
        }
    }

    if (tracks.error.isNotBlank()) {
        DataErrorHandler(error = tracks.error)
    }

    if (tracks.isLoading) {
        DataLoaderDisplay()
    }
}