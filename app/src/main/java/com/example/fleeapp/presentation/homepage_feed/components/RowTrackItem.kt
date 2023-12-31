package com.example.fleeapp.presentation.homepage_feed.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.fleeapp.domain.model.tracks.Track
import com.example.fleeapp.presentation.common_ui.AsyncAdjustableImageItem
import com.example.fleeapp.presentation.common_ui.theme.flee_main.FleeMainTheme
import com.example.fleeapp.presentation.homepage_feed.states.PreviewTrackState


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowTrackItem(
    track: Track,
    trackPlaying: PreviewTrackState<Track>,
    onTrackClick: () -> Unit,
    onTrackDoubleClick: (Track) -> Unit,
) {

    var shouldUiChange = trackPlaying.isNowPlaying
            && trackPlaying.track?.id == track.id

    var shouldDetailsShow = remember {
        mutableStateOf(false)
    }


    if (shouldDetailsShow.value)
        RowTrackItemDialog(
            track = track,
            onDismissRequest = {
                shouldDetailsShow.value = false
            }
        )

    Column(
        modifier = Modifier
            .padding(
                top = 7.dp,
                start = 15.dp,
                end = 15.dp,
                bottom = 10.dp
            )
            .width(FleeMainTheme.dimensions.columnWidth)
            .background(
                if (shouldUiChange)
                    FleeMainTheme.colors.backgroundAccentPrimary
                else FleeMainTheme.colors.backgroundSecondary
            )

    ) {
        track.let {
            AsyncAdjustableImageItem(
                imageUrl = it.image,
                contentDescription = it.name,
                modifier = Modifier
                    .height(FleeMainTheme.dimensions.columnWidth)
                    .combinedClickable(
                        onClick = { /**/ },
                        onLongClick = {
                            shouldDetailsShow.value = true
                        },
                        onDoubleClick = {
                            onTrackDoubleClick(track)
                        }
                    )
            )

            Text(
                text = it.name,
                color = if (shouldUiChange)
                    FleeMainTheme.colors.textAccentPrimary
                else FleeMainTheme.colors.textPrimary,

                style = FleeMainTheme.typography.p,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.padding(
                    top = 7.dp,
                    start = 15.dp,
                    end = 15.dp
                )
            )

            Text(
                text = it.artistName,
                color = if (shouldUiChange)
                    FleeMainTheme.colors.textAccentSecondary
                else FleeMainTheme.colors.textSecondary,

                style = FleeMainTheme.typography.small,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.padding(
                    start = 15.dp,
                    end = 15.dp,
                    bottom = 10.dp
                )
            )
        }

    }

}