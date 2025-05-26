package com.example.whatweeating.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatweeating.data.Recipe
import com.example.whatweeating.R

@Composable
fun PopularRecipes() {
    val popularRecipes = listOf(
        Recipe(
            name = "Spaghetti Carbonara",
            image = painterResource(id = R.drawable.carbonara),
            rating = 4.5,
            difficulty = "Średni",
            time = "25 min"
        ),
        Recipe(
            name = "Lasagne Bolognese",
            image = painterResource(id = R.drawable.lasagne),
            rating = 4.8,
            difficulty = "Trudny",
            time = "45 min"
        ),
        Recipe(
            name = "Ratatouille",
            image = painterResource(id = R.drawable.ratatouille),
            rating = 4.9,
            difficulty = "Średni",
            time = "35 min"
        ),
        Recipe(
            name = "Empanadas",
            image = painterResource(id = R.drawable.empanadas),
            rating = 4.8,
            difficulty = "Łatwy",
            time = "25 min"
        )
    )

    val topRatingRecipes = listOf(
        Recipe(
            name = "Ratatouille",
            image = painterResource(id = R.drawable.ratatouille),
            rating = 4.9,
            difficulty = "Średni",
            time = "35 min"
        ),
        Recipe(
            name = "Empanadas",
            image = painterResource(id = R.drawable.empanadas),
            rating = 4.8,
            difficulty = "Łatwy",
            time = "25 min"
        ),
        Recipe(
            name = "Lasagne Bolognese",
            image = painterResource(id = R.drawable.lasagne),
            rating = 4.8,
            difficulty = "Trudny",
            time = "45 min"
        ),
        Recipe(
            name = "Spaghetti Carbonara",
            image = painterResource(id = R.drawable.carbonara),
            rating = 4.5,
            difficulty = "Średni",
            time = "25 min"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 2.dp)
    ) {
        Text(
            text = "Popularne przepisy",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(popularRecipes) { popularRecipe ->
                RecipeCard(popularRecipe)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Najwyżej oceniane",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(topRatingRecipes) { topRecipe ->
                RecipeCard(topRecipe)
            }
        }
    }
}


@Composable
fun RecipeCard(recipe: Recipe) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(180.dp)
            .height(180.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = recipe.image,
                contentDescription = recipe.name,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, top = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = recipe.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Ocena",
                        tint = Color.Black,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${recipe.rating}", fontSize = 12.sp)

                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "•", fontSize = 12.sp, color = Color.Gray)

                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = recipe.difficulty, fontSize = 12.sp)

                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "•", fontSize = 12.sp, color = Color.Gray)

                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = recipe.time, fontSize = 12.sp)
                }
            }
        }
    }
}

