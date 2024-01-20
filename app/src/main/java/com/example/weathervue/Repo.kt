package com.example.weathervue

class Repo(
    private val retrofitServices: RetrofitServices
) {

    suspend fun getWeatherDeatils(city : String) = retrofitServices.getWeatherDetails(city)


}