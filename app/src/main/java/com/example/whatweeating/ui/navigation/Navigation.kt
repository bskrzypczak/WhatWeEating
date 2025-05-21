package com.example.whatweeating.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whatweeating.ui.screens.FavoritesScreen
import com.example.whatweeating.ui.screens.CommunityScreen
import com.example.whatweeating.ui.screens.CookingScreen
import com.example.whatweeating.ui.screens.ProfileScreen
import com.example.whatweeating.ui.screens.HomeScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.FavoritesScreen.route) {
            FavoritesScreen(navController = navController)
        }
        composable(route = Screen.CookingScreen.route) {
            CookingScreen(navController = navController)
        }
        composable(route = Screen.CommunityScreen.route) {
            CommunityScreen(navController = navController)
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
    }
}
