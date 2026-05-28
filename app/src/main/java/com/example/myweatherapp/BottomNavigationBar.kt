package com.example.myweatherapp

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp

@Composable
fun BottomNavigationBar(
    isSelectedMenu: Boolean,
    isSelectedSettings: Boolean,
    onMenuSelected: () -> Unit,
    onSettingsSelected: () -> Unit
) {

    NavigationBar {

        NavigationBarItem(
            selected = isSelectedMenu,

            onClick = {
                onMenuSelected()
            },

            icon = {
                Icon(
                    painter = painterResource(R.drawable.icons8_clouds_96),
                    contentDescription = "Menú"
                )
            },

            label = {
                Text(
                    text = "Clima",
                    fontSize = 10.sp
                )
            }
        )

        NavigationBarItem(
            selected = isSelectedSettings,

            onClick = {
                onSettingsSelected()
            },

            icon = {
                Icon(
                    painter = painterResource(R.drawable.icons8_ajustes_96),
                    contentDescription = "Ajustes"
                )
            },

            label = {
                Text(
                    text = "Ajustes",
                    fontSize = 10.sp
                )
            }
        )
    }
}