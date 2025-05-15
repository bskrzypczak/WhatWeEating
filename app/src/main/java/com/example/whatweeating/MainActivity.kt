package com.example.whatweeating

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.whatweeating.ui.screens.HomeScreen
import com.example.whatweeating.ui.theme.WhatWeEatingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhatWeEatingTheme {
                HomeScreen()
            }
        }
    }
}