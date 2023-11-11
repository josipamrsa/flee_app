package com.example.fleeapp.common.media_player

import com.example.fleeapp.domain.model.tracks.Track
import java.text.FieldPosition


interface AudioPlayer {
    fun playOrStopAudio(track: Track)

    fun playTenSecondPreview(
        track: Track,
        position: Long
    )
}