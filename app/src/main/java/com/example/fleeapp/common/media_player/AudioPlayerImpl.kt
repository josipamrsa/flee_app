package com.example.fleeapp.common.media_player

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import java.io.IOException

class AudioPlayerImpl(
    private val context: Context
) : AudioPlayer {

    private var player: MediaPlayer? = null

    override fun playAudio(url: String) {
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

    override fun stopAudio() {
        player?.apply {
            stop()
            release()
        }

        player = null
    }

    override fun playTenSecondPreview(url: String, start: Int, duration: Int) {

    }
}