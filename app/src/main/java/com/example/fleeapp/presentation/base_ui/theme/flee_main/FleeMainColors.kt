package com.example.fleeapp.presentation.base_ui.theme.flee_main

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf

import androidx.compose.ui.graphics.Color

class FleeMainColors(
    backgroundPrimary: Color,
    backgroundSecondary: Color,
    backgroundAccentPrimary: Color,
    textPrimary: Color,
    textAccentPrimary: Color,
    textSecondary: Color,
    textAccentSecondary: Color,
    navItemSelectedIcon: Color,
    navItemSelectedBackground: Color,
    navItemUnselectedIcon: Color,
) {
    var backgroundPrimary by mutableStateOf(backgroundPrimary)
        private set
    var backgroundSecondary by mutableStateOf(backgroundSecondary)
        private set
    var backgroundAccentPrimary by mutableStateOf(backgroundAccentPrimary)
        private set

    var textPrimary by mutableStateOf(textPrimary)
        private set
    var textAccentPrimary by mutableStateOf(textAccentPrimary)
        private set
    var textSecondary by mutableStateOf(textSecondary)
        private set
    var textAccentSecondary by mutableStateOf(textAccentSecondary)
        private set

    var navItemSelectedIcon by mutableStateOf(navItemSelectedIcon)
        private set
    var navItemSelectedBackground by mutableStateOf(navItemSelectedBackground)
        private set
    var navItemUnselectedIcon by mutableStateOf(navItemUnselectedIcon)
        private set


    fun copy(
        backgroundPrimary: Color = this.backgroundPrimary,
        backgroundSecondary: Color = this.backgroundSecondary,
        backgroundAccentPrimary: Color = this.backgroundAccentPrimary,
        textPrimary: Color = this.textPrimary,
        textAccentPrimary: Color = this.textAccentPrimary,
        textSecondary: Color = this.textSecondary,
        textAccentSecondary: Color = this.textAccentSecondary,
        navItemSelectedIcon: Color = this.navItemSelectedIcon,
        navItemSelectedBackground: Color = this.navItemSelectedBackground,
        navItemUnselectedIcon: Color = this.navItemUnselectedIcon
    ) : FleeMainColors = FleeMainColors(
        backgroundPrimary,
        backgroundSecondary,
        backgroundAccentPrimary,
        textPrimary,
        textAccentPrimary,
        textSecondary,
        textAccentSecondary,
        navItemSelectedIcon,
        navItemSelectedBackground,
        navItemUnselectedIcon
    )

    fun updateColorsFrom(other: FleeMainColors) {
        backgroundPrimary = other.backgroundPrimary
        backgroundSecondary = other.backgroundSecondary
        backgroundAccentPrimary = other.backgroundAccentPrimary

        textPrimary = other.textPrimary
        textAccentPrimary = other.textAccentPrimary
        textSecondary = other.textSecondary
        textAccentSecondary = other.textAccentSecondary

        navItemSelectedIcon = other.navItemSelectedIcon
        navItemSelectedBackground = other.navItemSelectedBackground
        navItemUnselectedIcon = other.navItemUnselectedIcon
    }
}


/// ** COLOR SCHEMES ** ///

/* BLUE RED SCHEME */

private val brWhite01 = Color.White

private val brBrightCoral01 = Color(0xFFE31D52)

private val brDarkBlue01 = Color(0xFF000C1C)
private val brDarkBlue02 = Color(0xFF0B1936)
private val brLightBlue01 = Color(0xFF80A5D3)

private val brMutedPurple02 = Color(0xFF330041)
private val brMutedPurple01 = Color(0xFF59086E)
private val brLightPurple01 = Color(0xFFA77BB3)


fun blueRedColorScheme(
    backgroundPrimary: Color = brDarkBlue01,
    backgroundSecondary: Color = brDarkBlue02,
    backgroundAccentPrimary: Color = brMutedPurple02,
    textPrimary: Color = brWhite01,
    textAccentPrimary: Color = brBrightCoral01,
    textSecondary: Color = brLightBlue01,
    textAccentSecondary: Color = brLightPurple01,
    navItemSelectedIcon: Color = brDarkBlue02,
    navItemSelectedBackground: Color = brLightPurple01,
    navItemUnselectedIcon: Color = brLightBlue01
) : FleeMainColors = FleeMainColors(
    backgroundPrimary,
    backgroundSecondary,
    backgroundAccentPrimary,
    textPrimary,
    textAccentPrimary,
    textSecondary,
    textAccentSecondary,
    navItemSelectedIcon,
    navItemSelectedBackground,
    navItemUnselectedIcon
)

val LocalColors = staticCompositionLocalOf { blueRedColorScheme() }
