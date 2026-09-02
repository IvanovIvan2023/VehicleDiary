package com.example.vehiclediary


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class CarEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val brand: String,
    val model: String,
    val year: Int,
    val plateNumber: String,
    val mileage: Int
)