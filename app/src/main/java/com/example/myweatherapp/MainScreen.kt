package com.example.myweatherapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyWeatherApp(modifier: Modifier = Modifier) {

    var isSelectedMenu by remember { mutableStateOf(true) }
    var isSelectedSettings by remember { mutableStateOf(false) }
    var inputTextField by remember { mutableStateOf("") }
    var displayCard by remember {mutableStateOf(false)}

    val listOfCities = remember {
        mutableStateListOf<Ciudad>()
    }

    Scaffold(
        topBar = {
            WeatherTopBar()
        },
        bottomBar = {
            BottomNavigationBar(
                isSelectedMenu = isSelectedMenu,
                isSelectedSettings = isSelectedSettings,
                onMenuSelected = {
                    isSelectedMenu = true
                    isSelectedSettings = false
                },
                onSettingsSelected = {
                    isSelectedMenu = false
                    isSelectedSettings = true
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Busca una ciudad para ver el clima",
                fontWeight = FontWeight.Light,
            )

            Spacer(Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = inputTextField,
                    onValueChange = {
                        inputTextField = it
                    },
                    placeholder = {
                        Text(
                            text = "Ciudad...",
                            fontSize = 14.sp
                        )
                    },
                    label = {
                        Text(
                            text = "Ingresar Ciudad"
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                )
                Button(
                    modifier = Modifier.padding(end = 16.dp),
                    onClick = {
                        if (inputTextField.isNotBlank()) {
                            fetchApiWeather(
                                city = inputTextField,
                                onResult = { ciudad ->
                                    listOfCities.add(ciudad)
                                }
                            )
                            inputTextField = ""
                            displayCard = true
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.boton_agregar),
                        contentDescription = "Agregar"
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Ciudades guardadas",
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(20.dp))

                SavedCitiesSection(cities = listOfCities, displayCard = displayCard)
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MyWeatherAppPreview() {
    MyWeatherAppTheme {
        MyWeatherApp()
    }
}