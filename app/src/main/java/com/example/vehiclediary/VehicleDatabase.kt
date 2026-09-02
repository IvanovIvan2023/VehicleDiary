package com.example.vehiclediary

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CarEntity::class],
    version = 1
)
abstract class VehicleDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao
}