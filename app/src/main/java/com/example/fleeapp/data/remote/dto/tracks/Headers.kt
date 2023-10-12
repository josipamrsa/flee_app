package com.example.fleeapp.data.remote.dto.tracks


import com.google.gson.annotations.SerializedName

data class Headers(
    @SerializedName("code")
    val code: Int,
    @SerializedName("error_message")
    val errorMessage: String,
    @SerializedName("results_count")
    val resultsCount: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("warnings")
    val warnings: String
)