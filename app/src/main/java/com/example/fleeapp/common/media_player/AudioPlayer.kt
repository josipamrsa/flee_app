package com.example.fleeapp.common.media_player

interface AudioPlayer {
    /*fun playAudio(
        url: String
    )

    fun stopAudio()*/

    fun playOrStopAudio(url: String, isToStop: Boolean)

    fun playTenSecondPreview(
        url: String,
        start: Int,
        duration: Int
    )
}