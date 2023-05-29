package com.example.havadurumu.api
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.havadurumu.api.models.MyWeather
import com.example.havadurumu.Dto.Citys
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){

    private val _post = MutableLiveData<MyWeather>()
    val post: LiveData<MyWeather>
        get() = _post

    private val _isloading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _isloading

    init {
        fetchWeather()
    }

    fun fetchWeather(){
        viewModelScope.launch {
            _isloading.value = true
            val weather: MyWeather = RetrofitInstance.api.fetchWeather("tr",Citys.city)
            _post.value = weather
            _isloading.value = false
        }
    }
}