package com.example.fleeapp.presentation.base_ui.theme.flee_main

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf

import androidx.compose.ui.graphics.Color

class FleeMainColors(
    backgroundPrimary: Color,
    textPrimary: Color,
    backgroundSecondary: Color,
    textAccentPrimary: Color,
    textAccentSecondary: Color,
    textSecondary: Color
) {
    var backgroundPrimary by mutableStateOf(backgroundPrimary)
        private set
    var textPrimary by mutableStateOf(textPrimary)
        private set
    var backgroundSecondary by mutableStateOf(backgroundSecondary)
        private set
    var textAccentPrimary by mutableStateOf(textAccentPrimary)
        private set
    var textAccentSecondary by mutableStateOf(textAccentSecondary)
        private set
    var textSecondary by mutableStateOf(textSecondary)
        private set

    fun copy(
        backgroundPrimary: Color = this.backgroundPrimary,
        textPrimary: Color = this.textPrimary,
        backgroundSecondary: Color = this.backgroundSecondary,
        textAccentPrimary: Color = this.textAccentPrimary,
        textAccentSecondary: Color = this.textAccentSecondary,
        textSecondary: Color = this.textSecondary
    ) : FleeMainColors = FleeMainColors(
        backgroundPrimary,
        textPrimary,
        backgroundSecondary,
        textAccentPrimary,
        textAccentSecondary,
        textSecondary
    )

    fun updateColorsFrom(other: FleeMainColors) {
        backgroundPrimary = other.backgroundPrimary
        textPrimary = other.textPrimary
        backgroundSecondary = other.backgroundSecondary
        textAccentPrimary = other.textAccentPrimary
        textAccentSecondary = other.textAccentSecondary
        textSecondary = other.textSecondary
    }

    /* BLUE RED SCHEME */


    private val blueRedBackgroundPrimary = Color(0xFF000C1C)
    private val blueRedTextPrimary = Color.White
    private val blueRedBackgroundSecondary = Color(0xFF0B1936)
    private val blueRedTextAccentPrimary = Color(0xFFE31D52)
    private val blueRedTextAccentSecondary = Color(0xFF59086E)
    private val blueRedTextSecondary = Color(0xFF4F6F96)

    fun blueRedColorScheme(
        backgroundPrimary: Color = blueRedBackgroundPrimary,
        textPrimary: Color = blueRedTextPrimary,
        backgroundSecondary: Color = blueRedBackgroundSecondary,
        textAccentPrimary: Color = blueRedTextAccentPrimary,
        textAccentSecondary: Color = blueRedTextAccentSecondary,
        textSecondary: Color = blueRedTextSecondary,
    ) : FleeMainColors = FleeMainColors(
        backgroundPrimary,
        textPrimary,
        backgroundSecondary,
        textAccentPrimary,
        textAccentSecondary,
        textSecondary
    )

    val LocalColors = staticCompositionLocalOf { blueRedColorScheme() }

}