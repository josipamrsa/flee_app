package com.example.fleeapp.presentation.homepage_feed

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
import com.example.fleeapp.presentation.common_ui.ListDisplayState
import com.example.fleeapp.presentation.homepage_feed.states.PreviewTrackState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomepageFeedViewModel @Inject constructor(
    private val getFeaturedTracksUseCase: GetFeaturedTracksUseCase,
    private val getPopularTracksUseCase: GetPopularTracksUseCase,
    private val getAcousticOnlyTracksUseCase: GetAcousticOnlyTracksUseCase

) : ViewModel() {

    private val _featuredTracks =
        mutableStateOf<ListDisplayState<Track>>(ListDisplayState())
    val featuredTracks: State<ListDisplayState<Track>> = _featuredTracks

    private val _popularTracks =
        mutableStateOf<ListDisplayState<Track>>(ListDisplayState())
    val popularTracks: State<ListDisplayState<Track>> = _popularTracks

    private val _acousticOnlyTracks =
        mutableStateOf<ListDisplayState<Track>>(ListDisplayState())
    val acousticOnlyTracks: State<ListDisplayState<Track>> = _acousticOnlyTracks

    private val _isNowPlayingTrack = MutableStateFlow(PreviewTrackState<Track>())
    val isNowPlayingTrack: StateFlow<PreviewTrackState<Track>> = _isNowPlayingTrack

    private val _filterableTitle = MutableStateFlow(String())
    val filterableTitle: StateFlow<String> = _filterableTitle


    private val player by lazy {
        AudioPlayerImpl(FleeApplication.appContext)
    }

    init {
        getFeaturedTracks()
        getPopularTracks()
        getAcousticOnlyTracks()

        getPlaybackInformation()
    }

    private fun getPlaybackInformation() {
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
        state: MutableState<ListDisplayState<Track>>,
        result: Resource<List<Track>>,
        filterable: Boolean = false,
        filterableOptions: Map<String, String> = emptyMap()
    ) {
        when (result) {
            is Resource.Success -> {
                state.value =
                    ListDisplayState(
                        data = result.data ?: emptyList(),
                        filterable = filterable,
                        filterableOptions = filterableOptions
                    )
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

    fun getPopularTracks(
        frequency: Map.Entry<String, String> =
            mapOf("popularity_week" to "Popular weekly").entries.first()
    ) {
        getPopularTracksUseCase(frequency.key).onEach { result ->
            handleResult(
                _popularTracks,
                result,
                filterable = true,
                filterableOptions = mapOf(
                    "popularity_week" to "Popular weekly",
                    "popularity_month" to "Popular monthly",
                    "popularity_total" to "Popular all-time"
                )
            ).also {
                _filterableTitle.value = frequency.value
            }
        }.launchIn(viewModelScope)
    }

    private fun getAcousticOnlyTracks() {
        getAcousticOnlyTracksUseCase().onEach { result ->
            handleResult(_acousticOnlyTracks, result)
        }.launchIn(viewModelScope)
    }

    fun onSetNowPlayingTrack(track: Track) {
        _isNowPlayingTrack.value = PreviewTrackState(track = track)
    }

    fun playTenSecondPreview(track: Track) {
        // Hacky, but will play the last 20 seconds of a song because
        // current ExoPlayer functionalities do not fully support song clips :/
        track.let {
            player.playTenSecondPreview(it, (it.duration * 1000 - 20000).toLong())
        }
    }
}