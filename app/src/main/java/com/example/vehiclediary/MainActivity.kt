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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType

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

    var brandText by remember {
        mutableStateOf("")
    }
    var modelText by remember {
        mutableStateOf("")
    }
    var yearText by remember {
        mutableStateOf("")
    }
    var plateNumberText by remember {
        mutableStateOf("")
    }
    var mileageText by remember {
        mutableStateOf("")
    }
    var errorText by remember {
        mutableStateOf("")
    }

    var car by remember {
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

        val currentCar = car
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
            VehicleTextField(
                value = brandText,
                onValueChange = {
                    brandText = it
                },
                label = "Марка автомобиля"
            )
            VehicleTextField(
                value = modelText,
                onValueChange = {
                    modelText = it
                },
                label = "Модель автомобиля"
            )
            VehicleTextField(
                value = yearText,
                onValueChange = {
                    yearText = it
                },
                label = "Год выпуска",
                keyboardType = KeyboardType.Number
            )
            VehicleTextField(
                value = plateNumberText,
                onValueChange = {
                    plateNumberText = it
                },
                label = "Госномер"
            )
            VehicleTextField(
                value = mileageText,
                onValueChange = {
                    mileageText = it
                },
                label = "Пробег",
                keyboardType = KeyboardType.Number
            )
        }

        Button(
            onClick = {
                if (currentCar != null) {
                    car = null
                } else {
                    val year = yearText.toIntOrNull()
                    val mileage = mileageText.toIntOrNull()
                    val errors = mutableListOf<String>()

                    if (brandText.isBlank()) {
                        errors.add("Введите марку автомобиля")
                    }

                    if (modelText.isBlank()) {
                        errors.add("Введите модель автомобиля")
                    }
                    if (year == null) {
                        errors.add("Введите корректный год")
                    }

                    if (plateNumberText.isBlank()) {
                        errors.add("Введите госномер")
                    }

                    if (mileage == null) {
                        errors.add("Введите корректный пробег")
                    }

                    errorText = errors.joinToString("\n")

                    if (errors.isEmpty() && year != null && mileage != null) {
                        car = Car(
                            brand = brandText,
                            model = modelText,
                            year = year,
                            mileage = mileage,
                            plateNumber = plateNumberText
                        )
                    }


                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                if (currentCar != null) {
                    "Удалить автомобиль"
                } else {
                    "Добавить автомобиль"
                }
            )
        }

        if (errorText.isNotEmpty()) {
            Text(
                text = errorText,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun VehicleTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(label)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        modifier = Modifier.padding(top = 16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun VehicleDiaryPreview() {
    VehicleDiaryTheme {
        VehicleDiaryScreen()
    }
}