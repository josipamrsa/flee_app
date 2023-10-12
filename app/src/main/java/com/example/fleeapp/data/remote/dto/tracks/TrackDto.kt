package com.example.fleeapp.data.remote.dto.tracks


import com.example.fleeapp.domain.model.tracks.Track
import com.google.gson.annotations.SerializedName

data class TrackDto(
    @SerializedName("album_id")
    val albumId: String,
    @SerializedName("album_image")
    val albumImage: String,
    @SerializedName("album_name")
    val albumName: String,
    @SerializedName("artist_id")
    val artistId: String,
    @SerializedName("artist_idstr")
    val artistIdStr: String,
    @SerializedName("artist_name")
    val artistName: String,
    @SerializedName("audio")
    val audio: String,
    @SerializedName("audiodownload")
    val audioDownload: String,
    @SerializedName("audiodownload_allowed")
    val audioDownloadAllowed: Boolean,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("license_ccurl")
    val licenseCcurl: String,
    @SerializedName("musicinfo")
    val musicInfo: MusicInfo,
    @SerializedName("name")
    val name: String,
    @SerializedName("position")
    val position: Int,
    @SerializedName("prourl")
    val proUrl: String,
    @SerializedName("releasedate")
    val releaseDate: String,
    @SerializedName("shareurl")
    val shareUrl: String,
    @SerializedName("shorturl")
    val shortUrl: String,
    @SerializedName("waveform")
    val waveform: String
)

fun TrackDto.toTrack(): Track {
    return Track(
        artistId = artistId,
        artistName = artistName,
        duration = duration,
        id = id,
        image = image,
        musicInfo = musicInfo,
        name = name,
        shortUrl = shortUrl
    )
}