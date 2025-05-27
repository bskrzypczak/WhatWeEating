package com.example.whatweeating.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whatweeating.ui.screens.FavoritesScreen
import com.example.whatweeating.ui.screens.CommunityScreen
import com.example.whatweeating.ui.screens.CookingScreen
import com.example.whatweeating.ui.screens.ProfileScreen
import com.example.whatweeating.ui.screens.HomeScreen
import com.example.whatweeating.ui.screens.RecipeScreen
import com.example.whatweeating.ui.viewmodels.SharedViewModel

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController, viewModel = sharedViewModel)
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
        composable("recipe_screen") {
            RecipeScreen(navController = navController, viewModel = sharedViewModel)
        }
    }
}
