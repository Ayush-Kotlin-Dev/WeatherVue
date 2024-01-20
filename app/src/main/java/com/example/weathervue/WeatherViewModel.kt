package com.example.weathervue

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathervue.dataModel.WeatherResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repo: Repo
) : ViewModel() {


    val weatherDetailsLiveData = MutableLiveData<WeatherResponseModel>()
    val isLoading = MutableLiveData<Boolean>(false)


    fun getWeatherDetails(city: String) {
        viewModelScope.launch(Dispatchers.IO) {


            //make loader visible here

            isLoading.postValue(true)
            //network request is sent
            val response = repo.getWeatherDeatils(city)

            if (response.isSuccessful) {


                //we have response from server
                weatherDetailsLiveData.postValue(response.body())


                //make loader invisible here
                isLoading.postValue(false)


            }

        }

    }
}