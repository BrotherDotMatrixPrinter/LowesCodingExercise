package com.funnycop.lowescodingexercise.ui.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.funnycop.lowescodingexercise.ui.theme.Alabaster
import com.funnycop.lowescodingexercise.ui.theme.RaisinBlack
import com.funnycop.lowescodingexercise.util.WEATHER_DETAIL_DESTINATION
import com.funnycop.lowescodingexercise.viewmodel.WeatherViewModel

@Composable
fun WeatherList(

    navController: NavController,
    weatherViewModel: WeatherViewModel

) {

    val isLightMode = MaterialTheme.colors.isLight

    val weather by weatherViewModel.weather.observeAsState()
    val cityName by weatherViewModel.cityName.observeAsState()

    Scaffold(

        topBar = {
            TopNavigationBar(

                title = "$cityName",
                navigateBack = { navController.navigateUp() }

            )
        }

    ) {

        LazyColumn {
            weather?.forEach {
                item {

                    Row(

                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .clickable {

                                weatherViewModel.setSelectedWeather(it)
                                navController.navigate(WEATHER_DETAIL_DESTINATION)

                            }

                    ) {

                        Text(

                            text = it.weather,
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)

                        )

                        Text(

                            text = "Temp: ${it.temperature}",
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(top = 16.dp, bottom = 16.dp)

                        )

                    }

                    Spacer(

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .border(

                                width = 1.dp,
                                color = if (isLightMode) RaisinBlack else Alabaster

                            )

                    )

                }
            }
        }

    }

}