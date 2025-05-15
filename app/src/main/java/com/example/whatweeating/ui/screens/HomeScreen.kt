package com.example.whatweeating.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.whatweeating.ui.components.GreetingHeader
import com.example.whatweeating.ui.components.SearchBar
import com.example.whatweeating.ui.components.BottomNavigationBar


@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = {
            BottomNavigationBar()
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
        }
    }
}