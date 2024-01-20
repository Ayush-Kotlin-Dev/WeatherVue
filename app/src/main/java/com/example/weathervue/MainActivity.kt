package com.example.weathervue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {


    private lateinit var loader : ProgressBar
    private lateinit var repo : Repo
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var weatherViewModelFactory: WeatherViewModelFactory

    private lateinit var edtCityName : EditText
    private lateinit var btnGetWeather : Button
    private lateinit var imgWeather : ImageView
    private lateinit var txtWeather : TextView

    private lateinit var txtCityStateName : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        init()


        btnGetWeather.setOnClickListener {
            weatherViewModel.getWeatherDetails(edtCityName.text.toString())

        }




        //Observer BLock
        weatherViewModel.weatherDetailsLiveData.observe(this ){
            val currentWeathertype = it.current.condition.text
            val currentTempInC = it.current.temp_c
            txtWeather.text = "$currentWeathertype , $currentTempInC"

            val imageLink = "https:${it.current.condition.icon}"
            Glide.with(this)
                .load(imageLink)
                .into(imgWeather)

            val cityName = it.location.name
            val state = it.location.region
            val cityState = "$cityName , $state"
            txtCityStateName.text= cityName
        }

        weatherViewModel.isLoading.observe(this){
            if(it){
                loader.visibility = View.VISIBLE
            }else{
                loader.visibility = View.GONE
            }
        }
    }
    private fun init(){
        loader = findViewById(R.id.loader)
        repo = Repo(RetrofitBuilder.getInstance())
        weatherViewModelFactory = WeatherViewModelFactory(repo)
        weatherViewModel = ViewModelProvider(this,weatherViewModelFactory).get(WeatherViewModel::class.java)


        edtCityName = findViewById(R.id.edtCityName)
        btnGetWeather = findViewById(R.id.btnGetWeather)
        imgWeather = findViewById(R.id.imgWeather)
        txtWeather = findViewById(R.id.txtWeather)

        txtCityStateName = findViewById(R.id.txtCityState)
    }
}