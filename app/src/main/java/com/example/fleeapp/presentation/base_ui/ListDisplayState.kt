package com.example.fleeapp.presentation.base_ui

import com.example.fleeapp.domain.model.tracks.Track

data class ListDisplayState<T>(
    val isLoading: Boolean = false,
    val data: List<T> = emptyList(),
    val error: String = ""
)