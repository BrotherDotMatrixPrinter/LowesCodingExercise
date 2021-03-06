package com.funnycop.lowescodingexercise.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.funnycop.lowescodingexercise.model.SimpleWeatherData
import com.funnycop.lowescodingexercise.repo.WeatherRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DEBUG_PROPERTY_NAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(

    private val weatherRepo: WeatherRepo

) : ViewModel() {

    private val _weather = MutableLiveData<List<SimpleWeatherData>?>(null)
    val weather: LiveData<List<SimpleWeatherData>?> get() = _weather

    private val _cityName = MutableLiveData("")
    val cityName: LiveData<String> get() = _cityName

    private val _selectedWeather = MutableLiveData<SimpleWeatherData?>(null)
    val selectedWeather: LiveData<SimpleWeatherData?> get() = _selectedWeather

    fun getForecast(code: String) = viewModelScope.launch(Dispatchers.IO) {

        try { weatherRepo.getForecast(code)?.let { _weather.postValue(it) } }
        catch (exception: Exception) { Log.d(DEBUG_PROPERTY_NAME, "$exception") }

    }

    fun setCityName(city: String) = _cityName.postValue(city)

    fun setSelectedWeather(weather: SimpleWeatherData) = _selectedWeather.postValue(weather)

}