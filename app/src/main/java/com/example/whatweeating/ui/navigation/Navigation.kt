package com.example.whatweeating.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.whatweeating.ui.screens.FavoritesScreen
import com.example.whatweeating.ui.screens.CommunityScreen
import com.example.whatweeating.ui.screens.CookingScreen
import com.example.whatweeating.ui.screens.ProfileScreen
import com.example.whatweeating.ui.screens.HomeScreen
import com.example.whatweeating.ui.screens.LogInScreen
import com.example.whatweeating.ui.screens.SignUpScreen
import com.example.whatweeating.ui.screens.RecipeScreen
import com.example.whatweeating.ui.screens.ResultsScreen
import com.example.whatweeating.ui.screens.TestScreen
import com.example.whatweeating.ui.viewmodels.AuthViewModel
import com.example.whatweeating.ui.viewmodels.SharedViewModel

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = viewModel()
    val modifier: Modifier

    NavHost(navController = navController, startDestination = Screen.LogInScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            val authViewModel: AuthViewModel = viewModel()
            HomeScreen(navController = navController, viewModel = sharedViewModel, authViewModel = authViewModel)
        }
        composable(route = Screen.FavoritesScreen.route) {
            val authViewModel: AuthViewModel = viewModel()
            FavoritesScreen(navController = navController, authViewModel = authViewModel)
        }
        composable(route = Screen.CookingScreen.route) {
            CookingScreen(navController = navController)
        }
        composable(route = Screen.CommunityScreen.route) {
            val authViewModel: AuthViewModel = viewModel()
            CommunityScreen(navController = navController, authViewModel = authViewModel)
        }
        composable(route = Screen.ProfileScreen.route) {
            val authViewModel: AuthViewModel = viewModel()
            ProfileScreen(navController = navController, authViewModel = authViewModel)
        }
        composable(route = Screen.RecipeScreen.route) {
            RecipeScreen(navController = navController, viewModel = sharedViewModel)
        }
        composable(route = Screen.LogInScreen.route){
            val authViewModel: AuthViewModel = viewModel()
            LogInScreen(modifier = Modifier, navController = navController, authViewModel = authViewModel)
        }
        composable(route = Screen.SignUpScreen.route){
            val authViewModel: AuthViewModel = viewModel()
            SignUpScreen(modifier = Modifier, navController = navController, authViewModel = authViewModel)
        }
        composable(route = Screen.ResultsScreen.route){
            ResultsScreen(navController = navController, recipes = sharedViewModel.recipes, viewModel = sharedViewModel)
        }
        composable(
            route = Screen.TestScreen.route + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: 0
            TestScreen(navController = navController, viewModel = sharedViewModel, recipeId = recipeId)
        }
    }
}
