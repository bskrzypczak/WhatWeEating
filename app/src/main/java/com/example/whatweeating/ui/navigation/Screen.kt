package com.example.whatweeating.ui.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object FavoritesScreen : Screen("favorites_screen")
    object CookingScreen : Screen("cooking_screen")
    object CommunityScreen : Screen("community_screen")
    object ProfileScreen : Screen("profile_screen")
}