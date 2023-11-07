package com.example.fleeapp.common.media_player

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ClippingMediaSource
import kotlinx.coroutines.flow.MutableStateFlow


class AudioPlayerImpl(
    private val context: Context
) : AudioPlayer {

    private val player = ExoPlayer.Builder(context).build()
    private val currentTrack = MutableStateFlow<String?>(null)

    private fun playTrack(
        url: String,
        isPreview: Boolean = false,
        position: Long = 0
    ) {
        currentTrack.value = url

        /*
            TODO revisit this in the future
            mediaItem.clippingConfiguration
                .buildUpon()
                .setStartPositionMs(1000)
                .setEndPositionMs(19000)
            */

        player.apply {
            val mediaItem = MediaItem.fromUri(url)
            setMediaItem(mediaItem)

            if (isPreview)
                seekTo(position)

            prepare()
            playWhenReady = true
        }
    }

    private fun stopTrack(
        url: String,
        isPreview: Boolean = false,
        position: Long = 0
    ) {
        player.apply {
            stop()
            playWhenReady = false

            if (currentTrack.value != url) {
                currentTrack.value = null
                playTrack(
                    url,
                    isPreview = isPreview,
                    position = position
                )
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

    override fun playTenSecondPreview(url: String, position: Long) {
        player.apply {
            if (playWhenReady || contentDuration == currentPosition)
                stopTrack(
                    url,
                    isPreview = true,
                    position = position
                )
            else playTrack(
                url,
                isPreview = true,
                position = position
            )
        }
    }
}