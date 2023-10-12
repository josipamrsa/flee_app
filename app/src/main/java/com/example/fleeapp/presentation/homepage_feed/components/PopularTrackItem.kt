package com.example.fleeapp.presentation.homepage_feed.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.fleeapp.domain.model.tracks.Track

@Composable
fun PopularTrackItem(
    track: Track,
    /*onItemClick: (Track) -> Unit*/
) {

    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
        Text(
            text = track.name,
            style = MaterialTheme.typography.headlineSmall,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = track.artistName,
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis
        )
    }

}