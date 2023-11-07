package com.example.fleeapp.common.media_player

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.flow.MutableStateFlow


class AudioPlayerImpl(
    private val context: Context
) : AudioPlayer {

    private val player = ExoPlayer.Builder(context).build()
    private val currentTrack = MutableStateFlow<String?>(null)

    private fun playTrack(url: String) {
        currentTrack.value = url

        player.apply {
            setMediaItem(MediaItem.fromUri(url))
            prepare()
            playWhenReady = true
        }
    }

    private fun stopTrack(url: String) {
        player.apply {
            stop()
            playWhenReady = false

            if (currentTrack.value != url) {
                currentTrack.value = null
                playTrack(url)
            } else {
                currentTrack.value = null
            }
        }
    }

    override fun playOrStopAudio(url: String) {
        player.apply {
            if (playWhenReady || contentDuration == currentPosition)
                stopTrack(url)
            else playTrack(url)
        }
    }

    override fun playTenSecondPreview(url: String, start: Long, duration: Long) {
        player.apply {
            seekTo(start).also { if (currentPosition == ) }
        }
    }
}