package com.example.whatweeating.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.whatweeating.ui.components.BottomNavigationBar
import com.example.whatweeating.ui.components.CategoriesList
import com.example.whatweeating.ui.components.CategoryResults
import com.example.whatweeating.ui.components.FiltersList
import com.example.whatweeating.ui.components.GreetingHeader
import com.example.whatweeating.ui.components.PopularRecipes
import com.example.whatweeating.ui.components.SearchBar
import com.example.whatweeating.ui.viewmodels.AuthState
import com.example.whatweeating.ui.viewmodels.AuthViewModel
import com.example.whatweeating.ui.viewmodels.SharedViewModel


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: SharedViewModel,
    authViewModel: AuthViewModel
) {
    val currentRoute = "home_screen"
    val authState by authViewModel.authState.observeAsState()
    val name by authViewModel.userName.observeAsState()
    val selectedCategory by viewModel.selectedCategory

    LaunchedEffect(authState) {
        if (authState is AuthState.Authenticated) {
            authViewModel.fetchUserName()
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                currentRoute = currentRoute
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                GreetingHeader(name = name ?: "użytkowniku")
            }

            item {
                SearchBar(onSearch = { query ->
                    viewModel.searchRecipes(query)
                    navController.navigate("results_screen")
                })
            }

            item {
                CategoriesList(
                    selectedCategory = selectedCategory,
                    onCategorySelected = { category ->
                        viewModel.selectCategory(category)
                        // Możesz wywołać od razu wyszukiwanie po kategorii, jeśli chcesz:
                        category?.let { viewModel.searchByCategory(it) }
                    }
                )
            }

            item {
                FiltersList()
            }

            item {
                if (selectedCategory != null) {
                    CategoryResults(
                        navController = navController,
                        viewModel = viewModel,
                        category = selectedCategory!!
                    )
                } else {
                    PopularRecipes(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}
