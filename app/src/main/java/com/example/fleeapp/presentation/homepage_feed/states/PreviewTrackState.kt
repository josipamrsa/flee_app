package com.example.fleeapp.presentation.homepage_feed.states

class PreviewTrackState<Track>(
    var isNowPlaying: Boolean = false,
    val track: Track? = null,
    val error: String = ""
)
