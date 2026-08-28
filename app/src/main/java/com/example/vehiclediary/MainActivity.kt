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

data class Car(
    val brand: String,
    val model: String,
    val year: Int
)

@Composable
fun VehicleDiaryScreen(
    modifier: Modifier = Modifier
) {
    val hasCar: Boolean = true

    val car = Car(
        brand = "Toyota",
        model = "Camry",
        year = 2018
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "VehicleDiary",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "Электронный дневник автомобиля",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )

        if (hasCar) {
            Text(
                text = "${car.brand} ${car.model}, ${car.year}",
                modifier = Modifier.padding(top = 32.dp)
            )
        } else {
            Text(
                text = "Автомобиль пока не добавлен",
                modifier = Modifier.padding(top = 32.dp)
            )
        }

        Button(
            onClick = {
                // Позже здесь будет переход
                // на экран добавления автомобиля.
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Добавить автомобиль")
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