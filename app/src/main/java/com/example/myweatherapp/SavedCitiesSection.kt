package com.example.myweatherapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SavedCitiesSection(
    cities: SnapshotStateList<Ciudad>,
    displayCard: Boolean
){
    Box(
        Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.LightGray),
        contentAlignment = Alignment.Center,
    ) {
        IsEmptyOrFullfilled(cities = cities, displayCard = displayCard)
    }
}
@Composable
fun IsEmptyOrFullfilled(cities: SnapshotStateList<Ciudad>, displayCard: Boolean){
    if(!displayCard){
        Column(
            modifier = Modifier
                .padding(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(R.drawable.icons8_parcialmente_nublado_lluvia_ios_17_outlined_72),
                contentDescription = "Not weather",
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            )
            Spacer(Modifier.height(10.dp))

            Text(
                text = "Aún no hay ciudades",
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "Busca una ciudad y añádela para ver el clima aquí",
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )
        }
    }else{
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(cities) {
                nameCity ->
                Card(
                    colors = cardColors(
                        containerColor = colorResource(R.color.dark_blue)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .padding(bottom = 15.dp)
                ) {
                    Text(
                        text = nameCity.nombreCiudad,
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "${nameCity.temperatura}º",
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}