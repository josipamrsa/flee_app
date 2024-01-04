package com.example.fleeapp.presentation.common_ui.theme.flee_main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember


object FleeMainTheme {
    val colors: FleeMainColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current

    val dimensions: FleeMainDimensions
    @Composable
    @ReadOnlyComposable
    get() = LocalDimensions.current

    val typography: FleeMainTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalTypography.current
}

@Composable
fun FleeMainTheme(
    colors: FleeMainColors = FleeMainTheme.colors,
    dimensions: FleeMainDimensions = FleeMainTheme.dimensions,
    typography: FleeMainTypography = FleeMainTheme.typography,
    content: @Composable () -> Unit
) {
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalDimensions provides dimensions,
        LocalTypography provides typography
    ) {
        content()
    }
}