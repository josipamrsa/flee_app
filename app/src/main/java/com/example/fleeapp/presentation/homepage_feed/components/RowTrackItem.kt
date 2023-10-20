package com.example.fleeapp.presentation.homepage_feed.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.fleeapp.domain.model.tracks.Track
import com.example.fleeapp.presentation.base_ui.AsyncImageSimpleItem

class ComponentWidth() {
    companion object {
        val columnWidth = 150.dp
        val imageWidth = 180.dp
    }
}

@Composable
fun RowTrackItem(
    track: Track,
    /*onItemClick: (Track) -> Unit*/
) {
    Column(
        modifier = Modifier
            .padding(
                top = 7.dp,
                start = 15.dp,
                end = 15.dp,
                bottom = 10.dp
            )
            .width(ComponentWidth.columnWidth)
    ) {

        AsyncImageSimpleItem(
            imageUrl = track.image,
            contentDescription = track.name,
            modifier = Modifier.size(ComponentWidth.imageWidth)
        )

        Text(
            text = track.name,
            style = MaterialTheme.typography.bodyLarge,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )

        Text(
            text = track.artistName,
            style = MaterialTheme.typography.bodySmall,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }

}