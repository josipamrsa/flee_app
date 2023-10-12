package com.example.fleeapp.presentation.homepage_feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fleeapp.common.Resource
import com.example.fleeapp.domain.model.tracks.Track
import com.example.fleeapp.domain.use_case.get_popular_tracks.GetPopularTracksUseCase
import com.example.fleeapp.presentation.base_ui.ListDisplayState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomepageFeedViewModel @Inject constructor(
    private val getPopularTracksUseCase: GetPopularTracksUseCase
) : ViewModel() {

    private val _popularTracks = mutableStateOf<ListDisplayState<Track>>(ListDisplayState())
    val popularTracks: State<ListDisplayState<Track>> = _popularTracks


    init {
        getPopularTracks()
    }

    private fun getPopularTracks() {
        getPopularTracksUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _popularTracks.value = ListDisplayState<Track>(data = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _popularTracks.value = ListDisplayState(error = result.message ?: "An unexpected error occured")
                }

                is Resource.Loading -> {
                    _popularTracks.value = ListDisplayState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}