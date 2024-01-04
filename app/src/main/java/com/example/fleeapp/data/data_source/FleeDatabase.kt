package com.example.fleeapp.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fleeapp.domain.model.tracks.Temp
import com.example.fleeapp.domain.model.tracks.Track

@Database(
    entities = [Temp::class],
    version = 1
)

abstract class FleeDatabase: RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "flee_db"
    }
}