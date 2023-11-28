package com.example.fleeapp.domain.model.tracks

enum class PopularityRating {
    POPULARITY_WEEK("popularity_week"),
    POPULARITY_MONTH("popularity_month"),
    POPULARITY_TOTAL("popularity_total");

    var frequency: String? = null

    constructor(frequency: String) {
        this.frequency = frequency
    }

    companion object {
        // call function from() without using period and/or brackets
        // much more like a natural language -> PopularityRating from frequency
        infix fun from(value: String): PopularityRating? =
            PopularityRating.values().firstOrNull() { it.frequency == value }
    }
}
