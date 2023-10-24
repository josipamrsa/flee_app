package com.example.fleeapp.presentation.homepage_feed

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fleeapp.FleeApplication
import com.example.fleeapp.common.Resource
import com.example.fleeapp.common.media_player.AudioPlayerImpl
import com.example.fleeapp.domain.model.tracks.Track
import com.example.fleeapp.domain.use_case.get_acoustic_only_tracks.GetAcousticOnlyTracksUseCase
import com.example.fleeapp.domain.use_case.get_featured_tracks.GetFeaturedTracksUseCase
import com.example.fleeapp.domain.use_case.get_popular_tracks.GetPopularTracksUseCase
import com.example.fleeapp.presentation.base_ui.ListDisplayState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomepageFeedViewModel @Inject constructor(
    private val getFeaturedTracksUseCase: GetFeaturedTracksUseCase,
    private val getPopularTracksUseCase: GetPopularTracksUseCase,
    private val getAcousticOnlyTracksUseCase: GetAcousticOnlyTracksUseCase

) : ViewModel() {

    private val _featuredTracks = mutableStateOf<ListDisplayState<Track>>(ListDisplayState())
    val featuredTracks: State<ListDisplayState<Track>> = _featuredTracks

    private val _popularTracks = mutableStateOf<ListDisplayState<Track>>(ListDisplayState())
    val popularTracks: State<ListDisplayState<Track>> = _popularTracks

    private val _acousticOnlyTracks = mutableStateOf<ListDisplayState<Track>>(ListDisplayState())
    val acousticOnlyTracks: State<ListDisplayState<Track>> = _acousticOnlyTracks

    private val player by lazy {
        AudioPlayerImpl(FleeApplication.appContext)
    }

    init {
        getFeaturedTracks()
        getPopularTracks()
        getAcousticOnlyTracks()
    }

    private fun handleResult(
        state: MutableState<ListDisplayState<Track>>,
        result: Resource<List<Track>>
    ) {
        when (result) {
            is Resource.Success -> {
                state.value =
                    ListDisplayState<Track>(data = result.data ?: emptyList())
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

    fun playAudio(url: String) {
        player.playAudio(url)
    }

    fun stopAudio() {
        player.stopAudio()
    }
}