package com.example.fleeapp.presentation.common_ui

open class ListDisplayState<T>(
    open val isLoading: Boolean = false,
    open val filterable: Boolean = false,
    open val filterableOptions: Map<String, String> = emptyMap(),
    open val data: List<T> = emptyList(),
    open val error: String = "",
)