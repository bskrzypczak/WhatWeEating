package com.example.whatweeating.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.whatweeating.ui.components.BottomNavigationBar
import com.example.whatweeating.ui.components.ProfileInfo
import com.example.whatweeating.R


@Composable
fun ProfileScreen(navController: NavController){
    var text by remember {
        mutableStateOf("")
    }
    val image = painterResource(id = R.drawable.kot)
    var currentRoute = "profile_screen"
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
            ProfileInfo(image, "email", "imie")
        }
    }
}