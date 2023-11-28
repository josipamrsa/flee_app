package com.example.fleeapp.presentation.homepage_feed.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.fleeapp.domain.model.tracks.Track
import com.example.fleeapp.presentation.base_ui.theme.flee_main.FleeMainTheme

@Composable
fun RowTrackItemDetails(track: Track) {
    Column() {
        Text(
            text = track.artistName,
            style = FleeMainTheme.typography.p,
            color = FleeMainTheme.colors.textAccentPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun RowTrackItemDialog(
    track: Track,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = FleeMainTheme.colors.backgroundSecondary
            ),
            modifier = Modifier
                .width(FleeMainTheme.dimensions.smallComponentWidth)
                .height(FleeMainTheme.dimensions.smallComponentWidth)
                .padding(20.dp)

        ) {
            RowTrackItemDetails(track)
        }
    }
}