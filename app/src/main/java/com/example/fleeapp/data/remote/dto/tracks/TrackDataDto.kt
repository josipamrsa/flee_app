package com.example.fleeapp.data.remote.dto.tracks


import com.google.gson.annotations.SerializedName

data class TrackDataDto(
    @SerializedName("headers")
    val headers: Headers,
    @SerializedName("results")
    val tracks: List<TrackDto>
)
