package com.example.fleeapp.domain.model.tracks

import com.example.fleeapp.data.remote.dto.tracks.MusicInfo

class Track (
    val albumImage: String,
    val albumName: String,
    val artistId: String,
    val artistName: String,
    val duration: Int,
    val id: String,
    val image: String,
    // TODO turn to domain model and add converter
    val musicInfo: MusicInfo?,
    val name: String,
    val releaseDate: String,
    val shortUrl: String,
    val audio: String,
)