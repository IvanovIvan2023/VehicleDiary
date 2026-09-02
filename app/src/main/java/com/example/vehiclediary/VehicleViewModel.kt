package com.example.vehiclediary

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class VehicleViewModel(
    private val repository: CarRepository
) : ViewModel() {

    var car by mutableStateOf<CarEntity?>(null)
        private set

    init {
        loadCar()
    }

    fun loadCar() {
        viewModelScope.launch {
            car = repository.getCar()
        }
    }

    fun saveCar(carEntity: CarEntity) {
        viewModelScope.launch {
            repository.insertCar(carEntity)
            car = carEntity
        }
    }

    fun deleteCar() {
        val currentCar = car ?: return

        viewModelScope.launch {
            repository.deleteCar(currentCar)
            car = null
        }
    }
}