package com.example.fleeapp.presentation.base_ui.theme.flee_main

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.fleeapp.BuildConfig
import com.example.fleeapp.R

val provider = GoogleFont.Provider(
    providerAuthority = BuildConfig.GOOGLE_FONT_PROVIDER_AUTHORITY,
    providerPackage = BuildConfig.GOOGLE_FONT_PROVIDER_PACKAGE,
    certificates = R.array.com_google_android_gms_fonts_certs
)

private val quicksandFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Quicksand"),
        fontProvider = provider,
        weight = FontWeight.Bold,
    )
)

private val pacificoFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Pacifico"),
        fontProvider = provider,
        weight = FontWeight.Bold,
    )
)

data class FleeMainTypography(
    val header: TextStyle = TextStyle(
    fontFamily = pacificoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),

    val h1: TextStyle = TextStyle(
        fontFamily = quicksandFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp
    ),

    val p: TextStyle = TextStyle(
        fontFamily = quicksandFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    val small: TextStyle = TextStyle(
        fontFamily = quicksandFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    ),

    val h6: TextStyle = TextStyle(
        fontFamily = quicksandFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    )
)

internal val LocalTypography = staticCompositionLocalOf { FleeMainTypography() }

