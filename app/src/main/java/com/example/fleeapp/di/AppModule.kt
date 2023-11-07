package com.example.fleeapp.di

import android.content.Context
import android.media.AudioAttributes
import androidx.media3.exoplayer.ExoPlayer
import com.example.fleeapp.BuildConfig
import com.example.fleeapp.data.remote.JamendoApi
import com.example.fleeapp.data.repository.TrackRepositoryImpl
import com.example.fleeapp.domain.repository.TrackRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideJamendoApi(): JamendoApi {

        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_JAMENDO_BASE)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val url = chain
                            .request()
                            .url
                            .newBuilder()
                            .addQueryParameter("client_id", BuildConfig.API_JAMENDO_CLIENT_ID)
                            .build()
                        chain.proceed(chain.request().newBuilder().url(url).build())
                    }
                    .addInterceptor(httpLoggingInterceptor)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JamendoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTrackRepository(api: JamendoApi): TrackRepository {
        return TrackRepositoryImpl(api)
    }

}