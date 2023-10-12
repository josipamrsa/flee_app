package com.example.fleeapp.data.remote

import com.example.fleeapp.data.remote.NetworkContract
import com.example.fleeapp.data.remote.dto.tracks.TrackDataDto
import retrofit2.http.GET
import retrofit2.http.Query


interface JamendoApi {
    @GET(NetworkContract.TRACKS_DATA)
    suspend fun getPopularTracks(
        @Query("order") order: String? = null,
    ): TrackDataDto

}