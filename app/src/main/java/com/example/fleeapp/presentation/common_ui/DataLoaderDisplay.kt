package com.example.fleeapp.presentation.common_ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fleeapp.presentation.common_ui.theme.flee_main.FleeMainTheme

@Composable
fun DataLoaderDisplay() {
    Row(
        modifier = Modifier
            .height(FleeMainTheme.dimensions.smallComponentWidth)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Loading...",
                style = FleeMainTheme.typography.h6,
                color = FleeMainTheme.colors.textAccentSecondary
            )
        }

    }
}