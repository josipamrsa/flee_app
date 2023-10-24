package com.example.fleeapp.presentation.track_discovery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.fleeapp.presentation.base_ui.theme.flee_main.FleeMainTheme

@Composable
fun TrackDiscoveryScreen(
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(FleeMainTheme.colors.backgroundPrimary)
    ) {
        Text(
            text = "Track discovery screen",
            color = FleeMainTheme.colors.textPrimary,
        )
    }
}