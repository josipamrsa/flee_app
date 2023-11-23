package com.example.fleeapp.common.media_player

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.session.PlaybackState
import android.os.IBinder
import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ClippingMediaSource
import com.example.fleeapp.domain.model.tracks.Track
import kotlinx.coroutines.flow.MutableStateFlow


class AudioPlayerImpl(
    private val context: Context
) : AudioPlayer {

    private val player = ExoPlayer.Builder(context).build()
    private val currentTrack = MutableStateFlow<Track?>(null)

    fun getPlayerInstance(): ExoPlayer = player

    override fun getPlaybackState() {
        player.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)
            }
        })
    }

    private fun playTrack(
        track: Track,
        isPreview: Boolean = false,
        position: Long = 0
    ) {
        currentTrack.value = track

        /*
            TODO revisit this in the future
            mediaItem.clippingConfiguration
                .buildUpon()
                .setStartPositionMs(1000)
                .setEndPositionMs(19000)
            */

        player.apply {
            val mediaItem = MediaItem.fromUri(track.audio)
            setMediaItem(mediaItem)

            if (isPreview)
                seekTo(position)

            prepare()
            playWhenReady = true
        }
    }

    private fun stopTrack(
        track: Track,
        isPreview: Boolean = false,
        position: Long = 0
    ) {
        player.apply {
            stop()
            playWhenReady = false

            if (currentTrack.value?.id != track.id) {
                currentTrack.value = null
                playTrack(
                    track,
                    isPreview = isPreview,
                    position = position
                )
            } else {
                currentTrack.value = null
            }
        }
    }

    override fun playOrStopAudio(track: Track) {
        /*player.apply {
            if (playWhenReady || contentDuration == currentPosition)
                stopTrack(track)
            else playTrack(track)
        }*/
    }

    override fun playTenSecondPreview(track: Track, position: Long) {
        player.apply {
            if (playWhenReady || contentDuration == currentPosition)
                stopTrack(
                    track,
                    isPreview = true,
                    position = position
                )
            else playTrack(
                track,
                isPreview = true,
                position = position
            )
        }
    }
}