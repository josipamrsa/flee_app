package com.example.fleeapp.common.media_player


interface AudioPlayer {
    fun playOrStopAudio(url: String)

    fun playTenSecondPreview(
        url: String,
        duration: Long
    )
}