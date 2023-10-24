package com.example.fleeapp.presentation.homepage_feed.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.fleeapp.domain.model.tracks.Track
import com.example.fleeapp.presentation.base_ui.AsyncAdjustableImageItem
import com.example.fleeapp.presentation.base_ui.theme.flee_main.FleeMainTheme


// TODO rework?
class ComponentSizes() {
    companion object {
        val columnWidth = 150.dp
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowTrackItem(
    track: Track,
    onTrackClick: () -> Unit,
    onTrackDoubleClick: (Track) -> Unit,
) {

    Column(
        modifier = Modifier
            .padding(
                top = 7.dp,
                start = 15.dp,
                end = 15.dp,
                bottom = 10.dp
            )
            .width(ComponentSizes.columnWidth)

    ) {
        AsyncAdjustableImageItem(
            imageUrl = track.image,
            contentDescription = track.name,
            modifier = Modifier
                .height(ComponentSizes.columnWidth)
                .combinedClickable(
                    onClick = { /**/ },
                    onDoubleClick = { onTrackDoubleClick(track) }
                )
        )

        Text(
            text = track.name,
            color = FleeMainTheme.colors.textPrimary,
            style = FleeMainTheme.typography.p,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )

        Text(
            text = track.artistName,
            color = FleeMainTheme.colors.textSecondary,
            style = FleeMainTheme.typography.small,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }

}