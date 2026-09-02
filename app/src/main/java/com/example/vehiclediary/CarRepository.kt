package com.example.vehiclediary

class CarRepository(
    private val carDao: CarDao
) {

    suspend fun insertCar(car: CarEntity) {
        carDao.insertCar(car)
    }
    suspend fun getCar(): CarEntity? {
        return carDao.getCar()
    }

    suspend fun deleteCar(car: CarEntity) {
        carDao.deleteCar(car)
    }
}