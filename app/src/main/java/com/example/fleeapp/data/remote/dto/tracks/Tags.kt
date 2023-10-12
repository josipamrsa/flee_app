package com.example.fleeapp.data.remote.dto.tracks


import com.google.gson.annotations.SerializedName

data class Tags(
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("instruments")
    val instruments: List<String>,
    @SerializedName("vartags")
    val vartags: List<String>
)