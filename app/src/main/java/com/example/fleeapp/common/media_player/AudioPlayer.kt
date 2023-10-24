package com.example.fleeapp.common.media_player

interface AudioPlayer {
    fun playAudio(
        url: String
    )

    fun stopAudio()

    fun playTenSecondPreview(
        url: String,
        start: Int,
        duration: Int
    )
}