package com.example.fleeapp.data.remote.dto.tracks


import com.google.gson.annotations.SerializedName

data class MusicInfo(
    @SerializedName("acousticelectric")
    val acousticelectric: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("lang")
    val lang: String,
    @SerializedName("speed")
    val speed: String,
    @SerializedName("tags")
    val tags: Tags,
    @SerializedName("vocalinstrumental")
    val vocalinstrumental: String
)