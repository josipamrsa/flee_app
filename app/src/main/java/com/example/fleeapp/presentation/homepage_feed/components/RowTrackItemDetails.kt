package com.example.fleeapp.presentation.homepage_feed.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import com.example.fleeapp.domain.model.tracks.Track
import com.example.fleeapp.presentation.base_ui.AsyncAdjustableImageItem
import com.example.fleeapp.presentation.base_ui.theme.flee_main.FleeMainTheme
import java.text.SimpleDateFormat

@Composable
fun RowTrackItemDetails(track: Track) {

    val duration = SimpleDateFormat("mm:ss")
        .format(track.duration.toLong() * 1000)

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(FleeMainTheme.colors.backgroundPrimary)
    ) {
        AsyncAdjustableImageItem(
            imageUrl = track.albumImage,
            contentDescription = track.name,
            contentScale = ContentScale.Crop,
            shouldOverlay = true
        )

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = track.name.uppercase(),
                style = FleeMainTheme.typography.h1,
                maxLines = 2,
                color = FleeMainTheme.colors.textAccentPrimary,
                textAlign = TextAlign.Center
            )

            Text(
                text = track.artistName,
                style = FleeMainTheme.typography.p,
                maxLines = 1,
                color = FleeMainTheme.colors.textPrimary,
                textAlign = TextAlign.Center
            )

            Text(
                text = track.albumName,
                style = FleeMainTheme.typography.p,
                maxLines = 1,
                color = FleeMainTheme.colors.textSecondary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Row() {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "song duration",
                    tint = FleeMainTheme.colors.textSecondary,
                )

                Text(
                    text = duration,
                    style = FleeMainTheme.typography.h6,
                    color = FleeMainTheme.colors.textSecondary,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}

@Composable
fun RowTrackItemDialog(
    track: Track,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        (LocalView.current.parent as DialogWindowProvider).window
            .setDimAmount(0.85f)

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(FleeMainTheme.dimensions.mediumComponentHeight)
                .padding(20.dp)

        ) {
            RowTrackItemDetails(track)
        }
    }
}