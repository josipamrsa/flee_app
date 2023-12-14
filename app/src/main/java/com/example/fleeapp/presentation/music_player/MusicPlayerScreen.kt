package com.example.fleeapp.presentation.music_player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.fleeapp.presentation.common_ui.theme.flee_main.FleeMainTheme

@Composable
fun MusicPlayerScreen(
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
            text = "Music player screen",
            color = FleeMainTheme.colors.textPrimary,
        )
    }
}