package com.example.havadurumu


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.OnClickListener
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.havadurumu.Dto.Citys
import com.example.havadurumu.RecyclerView.MyAdapter
import com.example.havadurumu.RecyclerView.OnItemClickListener
import com.example.havadurumu.api.MainViewModel
import com.example.havadurumu.databinding.ActivityFifeDaysWeatherBinding

class FifeDaysWeather : AppCompatActivity() {
    lateinit var binding: ActivityFifeDaysWeatherBinding
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFifeDaysWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionbar = supportActionBar
        actionbar?.title = Citys.city.uppercase()
        mainViewModel.post.observe(this){weather->
            Citys.myWeather = weather
        }
        mainViewModel.loading.observe(this){_isloading->
            binding.progressBar.isVisible = _isloading
            if(!_isloading){
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
                val adapter = MyAdapter(object: OnItemClickListener{
                    override fun onItemClick(position: Int) {
                        Citys.position = position
                        getWeatherDetailPage()
                    }},Citys.myWeather!!.result, this)
                binding.recyclerView.adapter = adapter
            }
        }
    }
    fun getWeatherDetailPage(){
        var view = Intent(applicationContext,WeatherDetails::class.java)
        startActivity(view)
    }
}