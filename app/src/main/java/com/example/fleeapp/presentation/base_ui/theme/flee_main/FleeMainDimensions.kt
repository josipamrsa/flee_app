package com.example.fleeapp.presentation.base_ui.theme.flee_main

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class FleeMainDimensions(
    // COLUMNS AND ROWS //
    val columnWidth: Dp = 220.dp,
    val smallComponentWidth: Dp = 210.dp,

    // IMAGE SIZES //
    val imageSize: Int = 600
)

internal val LocalDimensions = staticCompositionLocalOf { FleeMainDimensions() }