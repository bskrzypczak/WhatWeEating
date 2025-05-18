package com.example.whatweeating.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorites : Screen("favorites")
    object Cooking : Screen("cooking")
    object Community : Screen("community")
    object Profile : Screen("profile")
}