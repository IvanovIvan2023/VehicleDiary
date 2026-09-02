package com.example.vehiclediary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VehicleViewModelFactory(
    private val repository: CarRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VehicleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VehicleViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}