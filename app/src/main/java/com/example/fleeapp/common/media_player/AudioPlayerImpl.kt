package com.example.fleeapp.common.media_player

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri


class AudioPlayerImpl(
    private val context: Context
) : AudioPlayer {

    private var player: MediaPlayer? = null

    private fun playAudio(url: String) {
        player = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )

            setDataSource(context, Uri.parse(url))
            prepare()
            start()
        }
    }

    private fun stopAudio() {
        player?.apply {
            stop()
            reset()
            release()
        }

        player = null
    }

    override fun playOrStopAudio(url: String, isToStop: Boolean) {
        if (isToStop) stopAudio() else playAudio(url)
    }

    override fun playTenSecondPreview(url: String, start: Int, duration: Int) {

    }
}