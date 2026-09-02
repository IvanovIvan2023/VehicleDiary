package com.example.vehiclediary

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete

@Dao
interface CarDao {
    @Insert
    suspend fun insertCar(car: CarEntity)

    @Query("SELECT * FROM cars LIMIT 1")
    suspend fun getCar(): CarEntity?

    @Delete
    suspend fun deleteCar(car: CarEntity)
}