package com.example.whatweeating.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.whatweeating.data.RecipeAPI
import androidx.navigation.NavController
import com.example.whatweeating.ui.components.BottomNavigationBar
import com.example.whatweeating.ui.viewmodels.SharedViewModel

@Composable
fun ResultsScreen(navController: NavController, recipes: List<RecipeAPI>, viewModel: SharedViewModel) {
    val currentRoute = "results_screen"

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                currentRoute = currentRoute
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .padding(innerPadding)  // uwzględniamy padding od scaffolda
        ) {
            Text(
                text = "Wyniki wyszukiwania",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 12.dp, top = 24.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(recipes) { recipe ->
                    Card(
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier
                            .height(180.dp)
                            .fillMaxWidth()
                            .clickable {
                                viewModel.selectRecipeApi(recipe)
                                navController.navigate("test_screen/${recipe.id}")
                            }
                    ) {
                        Column {
                            AsyncImage(
                                model = recipe.image,
                                contentDescription = recipe.title,
                                modifier = Modifier
                                    .height(140.dp)
                                    .fillMaxWidth()
                            )
                            Text(
                                text = recipe.title,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
