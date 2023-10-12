package com.example.fleeapp.domain.use_case.get_popular_tracks

import com.example.fleeapp.common.Resource
import com.example.fleeapp.data.remote.dto.tracks.toTrack
import com.example.fleeapp.domain.model.tracks.PopularityRating
import com.example.fleeapp.domain.model.tracks.Track
import com.example.fleeapp.domain.repository.TrackRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPopularTracksUseCase @Inject constructor(
    private val repository: TrackRepository
) {
    operator fun invoke(
        popularityRating: PopularityRating = PopularityRating.POPULARITY_WEEK
    ): Flow<Resource<List<Track>>> =
        flow {
            try {
                emit(Resource.Loading<List<Track>>())
                val tracks = repository.getPopularTracks(popularityRating).map { it.toTrack() }
                emit(Resource.Success<List<Track>>(tracks))
            } catch (e: HttpException) {
                emit(Resource.Error<List<Track>>(e.localizedMessage ?: "An unexpected error occured"))
            } catch (e: IOException) {
                emit(Resource.Error<List<Track>>("Couldn't reach server - check your Internet connection"))
            }
        }
}