package com.example.fleeapp.presentation.common_ui.theme.flee_main

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class FleeMainDimensions(
    // COLUMNS AND ROWS //
    val columnWidth: Dp = 220.dp,

    // COMPONENTS //
    val smallComponentWidth: Dp = 210.dp,
    val smallComponentHeight: Dp = 210.dp,
    val mediumComponentWidth: Dp = 420.dp,
    val mediumComponentHeight: Dp = 420.dp,

    // IMAGE SIZES //
    val imageSize: Int = 600
)

internal val LocalDimensions = staticCompositionLocalOf { FleeMainDimensions() }