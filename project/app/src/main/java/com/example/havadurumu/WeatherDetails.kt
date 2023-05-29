package com.example.havadurumu

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.havadurumu.Dto.Citys
import com.example.havadurumu.databinding.ActivityWeatherDetailsBinding

class WeatherDetails : AppCompatActivity() {
    lateinit var binding: ActivityWeatherDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        actionBar?.title = Citys.city.uppercase()
        setDetails()
    }
    private fun setDetails(){
        binding.dateDetails.text = Citys.myWeather!!.result[Citys.position].date
        binding.dayDetail.text = Citys.myWeather!!.result[Citys.position].day
        binding.statusDetail.text = Citys.myWeather!!.result[Citys.position].status

        var degree = Citys.myWeather!!.result[Citys.position].degree.toDouble()
        var degreeInt : Int = degree.toInt()
        binding.degreeDetails.text = "$degreeInt°"
        binding.humidityDetail.text = Citys.myWeather!!.result[Citys.position].humidity

        degree = Citys.myWeather!!.result[Citys.position].night.toDouble()
        degreeInt = degree.toInt()
        binding.degreeNGthDetail.text ="/$degreeInt°"

        degree = Citys.myWeather!!.result[Citys.position].max.toDouble()
        degreeInt = degree.toInt()
        binding.maxDegree.text = "H:$degreeInt°"

        degree = Citys.myWeather!!.result[Citys.position].min.toDouble()
        degreeInt = degree.toInt()
        binding.minDegree.text = "L:$degreeInt°"

        binding.description.text = Citys.myWeather!!.result[Citys.position].description.uppercase()

        val desc =  Citys.myWeather!!.result[Citys.position].description
        var image = getStatusImage(Citys.myWeather!!.result[Citys.position].status,desc)
        binding.weatherIconDetail.setImageResource(image)
    }

    private fun getStatusImage(status:String,desc:String):Int{
        val stat = status.lowercase()
        val desc = desc.lowercase()
        if(stat.equals("clear")||stat.equals("sunny"))
            return R.drawable.gunesli
        else if(stat.equals("clouds") || stat.equals("cloudy"))
            return R.drawable.bulutlu
        else if(stat.equals("rain") && desc.equals("hafif yağmur"))
            return R.drawable.yagmurlu
        else if(stat.equals("rain") && desc.endsWith("şiddetli yağmur"))
            return R.drawable.sagnak_yagmur
        else if(stat.equals("rain") && desc.equals("şiddetli yağmur"))
            return R.drawable.gokgurultulu
        else if(stat.equals("snow")||stat.equals("snowy"))
            return R.drawable.karlihava
        else
            return R.drawable.gokgurultulu
    }
}