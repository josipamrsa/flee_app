package com.example.fleeapp.presentation.homepage_feed

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import com.example.fleeapp.FleeApplication
import com.example.fleeapp.common.Resource
import com.example.fleeapp.common.media_player.AudioPlayerImpl
import com.example.fleeapp.domain.model.tracks.Track
import com.example.fleeapp.domain.use_case.get_acoustic_only_tracks.GetAcousticOnlyTracksUseCase
import com.example.fleeapp.domain.use_case.get_featured_tracks.GetFeaturedTracksUseCase
import com.example.fleeapp.domain.use_case.get_popular_tracks.GetPopularTracksUseCase
import com.example.fleeapp.presentation.base_ui.ListDisplayState
import com.example.fleeapp.presentation.homepage_feed.states.PreviewTrackState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.time.Duration
import javax.inject.Inject

@HiltViewModel
class HomepageFeedViewModel @Inject constructor(
    private val getFeaturedTracksUseCase: GetFeaturedTracksUseCase,
    private val getPopularTracksUseCase: GetPopularTracksUseCase,
    private val getAcousticOnlyTracksUseCase: GetAcousticOnlyTracksUseCase

) : ViewModel() {

    private val _featuredTracks =
        mutableStateOf<ListDisplayState<PreviewTrackState<Track>>>(ListDisplayState())
    val featuredTracks: State<ListDisplayState<PreviewTrackState<Track>>> = _featuredTracks

    private val _popularTracks =
        mutableStateOf<ListDisplayState<PreviewTrackState<Track>>>(ListDisplayState())
    val popularTracks: State<ListDisplayState<PreviewTrackState<Track>>> = _popularTracks

    private val _acousticOnlyTracks =
        mutableStateOf<ListDisplayState<PreviewTrackState<Track>>>(ListDisplayState())
    val acousticOnlyTracks: State<ListDisplayState<PreviewTrackState<Track>>> = _acousticOnlyTracks

    private val _isNowPlayingTrack = MutableStateFlow(PreviewTrackState<Track>())
    val isNowPlayingTrack: StateFlow<PreviewTrackState<Track>> = _isNowPlayingTrack

    /*
        TODO logic
            > Add state to keep track of current playing track
            > Change state depending on playback information
            > Use this info to change appearance 
    */

    private val player by lazy {
        AudioPlayerImpl(FleeApplication.appContext)
    }

    init {
        getFeaturedTracks()
        getPopularTracks()
        getAcousticOnlyTracks()

        getPlaybackInformation()
    }

    fun getPlaybackInformation() {
        player.getPlayerInstance().addListener(
            object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    super.onPlaybackStateChanged(playbackState)

                    when (playbackState) {
                        Player.STATE_READY -> _isNowPlayingTrack.value = PreviewTrackState(
                            true,
                            _isNowPlayingTrack.value.track,
                        )
                        Player.STATE_ENDED -> _isNowPlayingTrack.value = PreviewTrackState(
                            false,
                            _isNowPlayingTrack.value.track,
                        )
                    }
                }
            }
        )
    }

    private fun handleResult(
        state: MutableState<ListDisplayState<PreviewTrackState<Track>>>,
        result: Resource<List<Track>>
    ) {
        when (result) {
            is Resource.Success -> {
                val tracks = result.data?.map {
                    PreviewTrackState(track = it)
                }

                state.value =
                    ListDisplayState<PreviewTrackState<Track>>(data = tracks ?: emptyList())
            }

            is Resource.Error -> {
                state.value =
                    ListDisplayState(error = result.message ?: "An unexpected error occured")
            }

            is Resource.Loading -> {
                state.value = ListDisplayState(isLoading = true)
            }
        }
    }

    private fun getFeaturedTracks() {
        getFeaturedTracksUseCase().onEach { result ->
            handleResult(_featuredTracks, result)
        }.launchIn(viewModelScope)
    }

    private fun getPopularTracks() {
        getPopularTracksUseCase().onEach { result ->
            handleResult(_popularTracks, result)
        }.launchIn(viewModelScope)
    }

    private fun getAcousticOnlyTracks() {
        getAcousticOnlyTracksUseCase().onEach { result ->
            handleResult(_acousticOnlyTracks, result)
        }.launchIn(viewModelScope)
    }

    fun onSetNowPlayingTrack(wrapped: PreviewTrackState<Track>) {
        _isNowPlayingTrack.value = wrapped
    }

    fun playTenSecondPreview(wrapped: PreviewTrackState<Track>) {
        // Hacky, but will play the last 20 seconds of a song because
        // current ExoPlayer functionalities do not fully support song clips :/
        wrapped.track?.let {
            player.playTenSecondPreview(it, (it.duration * 1000 - 20000).toLong())
        }
    }
}