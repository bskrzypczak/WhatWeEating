package com.example.whatweeating

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.whatweeating.ui.components.BottomNavigationBar
import com.example.whatweeating.ui.navigation.AppNavGraph
import com.example.whatweeating.ui.navigation.Screen
import com.example.whatweeating.ui.screens.HomeScreen
import com.example.whatweeating.ui.theme.WhatWeEatingTheme
import androidx.compose.runtime.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhatWeEatingTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController, currentRoute ?: Screen.Home.route)
                    }
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        AppNavGraph(navController)
                    }
                }
            }
        }

    }
}