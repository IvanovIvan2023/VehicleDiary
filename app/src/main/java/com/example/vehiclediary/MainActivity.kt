package com.example.vehiclediary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vehiclediary.ui.theme.VehicleDiaryTheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            VehicleDiaryTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    VehicleDiaryScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun VehicleDiaryScreen(
    modifier: Modifier = Modifier
) {

    val car = remember {
        mutableStateOf<Car?>(
            Car(
                brand = "Toyota",
                model = "Camry",
                year = 2018,
                plateNumber = "O831KY29",
                mileage = 126000
            )
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "VehicleDiary", style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "Электронный дневник автомобиля",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )

        val currentCar = car.value
        if (currentCar != null) {
            Text(
                text = "${currentCar.brand} ${currentCar.model}, ${currentCar.year}",
                modifier = Modifier.padding(top = 32.dp)
            )
            Text(
                text = currentCar.plateNumber
            )
            Text(
                text = "Пробег: ${currentCar.mileage} км"
            )
        } else {
            Text(
                text = "Автомобиль пока не добавлен", modifier = Modifier.padding(top = 32.dp)
            )
        }

        Button(
            onClick = {
                if (car.value != null) {
                    car.value = null
                } else {
                    car.value = Car(
                        brand = "Honda",
                        model = "FR-V",
                        year = 2006,
                        mileage = 310000,
                        plateNumber = "O831KY29"
                    )
                }
            }, modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                if (currentCar != null) {
                    "Удалить автомобиль"
                } else {
                    "Добавить автомобиль"
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VehicleDiaryPreview() {
    VehicleDiaryTheme {
        VehicleDiaryScreen()
    }
}