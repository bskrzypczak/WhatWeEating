package com.example.whatweeating.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.whatweeating.ui.components.BottomNavigationBar
import com.example.whatweeating.ui.components.CategoriesList
import com.example.whatweeating.ui.components.FiltersList
import com.example.whatweeating.ui.components.GreetingHeader
import com.example.whatweeating.ui.components.SearchBar
import com.example.whatweeating.ui.components.SettingsList


@Composable
fun HomeScreen(navController: NavController){
    var currentRoute = "home_screen"
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                currentRoute = currentRoute
            )
        },
        modifier = Modifier.fillMaxSize())
    { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            GreetingHeader(name = "Bartek")
            SearchBar()
            CategoriesList()
            FiltersList()
        }
    }
}
