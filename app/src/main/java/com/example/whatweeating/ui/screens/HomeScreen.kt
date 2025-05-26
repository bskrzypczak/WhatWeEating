package com.example.whatweeating.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.whatweeating.ui.components.BottomNavigationBar
import com.example.whatweeating.ui.components.CategoriesList
import com.example.whatweeating.ui.components.FiltersList
import com.example.whatweeating.ui.components.GreetingHeader
import com.example.whatweeating.ui.components.PopularRecipes
import com.example.whatweeating.ui.components.SearchBar
import com.example.whatweeating.ui.components.WorldCuisines


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

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { GreetingHeader(name = "Bartek") }
            item { SearchBar() }
            item { CategoriesList() }
            item { FiltersList() }
            item { PopularRecipes() }
            item { WorldCuisines() }
        }
    }
}
