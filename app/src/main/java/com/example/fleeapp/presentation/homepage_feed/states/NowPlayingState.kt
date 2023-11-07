package com.example.fleeapp.presentation.homepage_feed.states

import com.example.fleeapp.domain.model.tracks.Track

data class NowPlayingState (
    val isNotPlaying: Boolean = true,
    val track: Track? = null,
    val error: String = ""
)