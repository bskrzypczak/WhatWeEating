package com.example.whatweeating.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.whatweeating.ui.screens.CommunityScreen
import com.example.whatweeating.ui.screens.CookingScreen
import com.example.whatweeating.ui.screens.FavoritesScreen
import com.example.whatweeating.ui.screens.HomeScreen
import com.example.whatweeating.ui.screens.ProfileScreen

// ui/navigation/NavGraph.kt
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen() }
        composable(Screen.Favorites.route) { FavoritesScreen() }
        composable(Screen.Cooking.route) { CookingScreen() }
        composable(Screen.Community.route) { CommunityScreen() }
        composable(Screen.Profile.route) { ProfileScreen() }
    }
}
