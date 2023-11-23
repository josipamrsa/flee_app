package com.example.fleeapp.presentation.base_ui

open class ListDisplayState<T>(
    open val isLoading: Boolean = false,
    open val data: List<T> = emptyList(),
    open val error: String = ""
)