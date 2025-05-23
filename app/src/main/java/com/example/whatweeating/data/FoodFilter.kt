package com.example.whatweeating.data

import androidx.compose.ui.graphics.vector.ImageVector

data class FoodFilter (
    val name: String,
    val icon: ImageVector,
    val options: List<String>
)