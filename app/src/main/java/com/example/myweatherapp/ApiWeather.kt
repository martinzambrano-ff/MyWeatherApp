package com.example.myweatherapp

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import org.json.JSONObject

fun fetchApiWeather(city: String, onResult: (Ciudad) -> Unit) {

    val API_KEY = BuildConfig.API_KEY_WEATHER
    val url = "${BuildConfig.URL_CONNECTION}$API_KEY&query=$city"

    val client = OkHttpClient()

    val request = Request.Builder()
        .url(url)
        .build()

    client.newCall(request).enqueue(object : Callback {

        override fun onResponse(call: Call, response: Response) {

            if (!response.isSuccessful) {
                throw IOException("La llamada no se pudo realizar correctamente")
            }

            val body = response.body?.string()

            if (body != null) {

                val json = JSONObject(body)

                val location = json.getJSONObject("location")
                val current = json.getJSONObject("current")

                val cityName = location.getString("name")
                val temperature = current.getInt("temperature")

                val ciudad = Ciudad(
                    nombreCiudad = cityName,
                    temperatura = temperature
                )
                onResult(ciudad)
            }
        }

        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }
    })
}