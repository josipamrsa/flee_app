package com.example.fleeapp.domain.model.tracks

import com.example.fleeapp.data.remote.dto.tracks.MusicInfo

class Track (
    val artistId: String,
    val artistName: String,
    val duration: Int,
    val id: String,
    val image: String,
    // TODO turn to domain model and add converter
    val musicInfo: MusicInfo?,
    val name: String,
    val shortUrl: String,
)