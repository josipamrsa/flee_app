package com.example.fleeapp.data.repository

import com.example.fleeapp.data.remote.JamendoApi
import com.example.fleeapp.data.remote.dto.tracks.TrackDataDto
import com.example.fleeapp.data.remote.dto.tracks.TrackDto
import com.example.fleeapp.domain.model.tracks.PopularityRating
import com.example.fleeapp.domain.repository.TrackRepository
import javax.inject.Inject

class TrackRepositoryImpl @Inject constructor(
    private val api: JamendoApi
) : TrackRepository {
    override suspend fun getPopularTracks(popularityRating: PopularityRating): List<TrackDto> {
        val tracks = api.getPopularTracks(popularityRating.frequency)
        return tracks.tracks
    }
}