package com.example.fleeapp.domain.repository

import com.example.fleeapp.data.remote.dto.tracks.TrackDataDto
import com.example.fleeapp.data.remote.dto.tracks.TrackDto
import com.example.fleeapp.domain.model.tracks.PopularityRating

interface TrackRepository {
    suspend fun getPopularTracks(frequency: PopularityRating): List<TrackDto>

}