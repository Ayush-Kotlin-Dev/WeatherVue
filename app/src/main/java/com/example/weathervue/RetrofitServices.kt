package com.example.weathervue

import com.example.weathervue.dataModel.WeatherResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {

    @GET("/v1/current.json?key=79da03221b334cec951132816241801")
    suspend fun getWeatherDetails(
        @Query("q") city: String

    ): Response<WeatherResponseModel>
}