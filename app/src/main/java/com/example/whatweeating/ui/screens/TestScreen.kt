package com.example.whatweeating.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.whatweeating.data.FavoriteRecipe
import com.example.whatweeating.ui.components.BottomNavigationBar
import com.example.whatweeating.ui.viewmodels.SharedViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@SuppressLint("DefaultLocale")
@Composable
fun TestScreen(
    navController: NavController,
    viewModel: SharedViewModel,
    recipeId: Int
) {
    val recipeDetails by viewModel.recipeDetails
    val userUid = FirebaseAuth.getInstance().currentUser?.uid
    val db = FirebaseFirestore.getInstance()

    val isFavorite = remember { mutableStateOf(false) }

    LaunchedEffect(recipeId) {
        viewModel.getRecipeDetails(recipeId)

        if (userUid != null) {
            Log.d("TestScreen", "Sprawdzanie czy przepis $recipeId jest w ulubionych dla $userUid")
            db.collection("users")
                .document(userUid)
                .collection("favorites")
                .document(recipeId.toString())
                .get()
                .addOnSuccessListener { document ->
                    isFavorite.value = document.exists()
                    Log.d("TestScreen", "Przepis w ulubionych: ${isFavorite.value}")
                }
                .addOnFailureListener { e ->
                    Log.e("TestScreen", "Błąd podczas sprawdzania ulubionych: ${e.message}")
                }
        }
    }

    fun addToFavorites(recipe: FavoriteRecipe) {
        if (userUid == null) {
            Log.w("TestScreen", "Użytkownik nie jest zalogowany")
            return
        }
        db.collection("users")
            .document(userUid)
            .collection("favorites")
            .document(recipe.id.toString())
            .set(recipe)
            .addOnSuccessListener {
                Log.d("TestScreen", "Dodano do ulubionych: ${recipe.title}")
                isFavorite.value = true
            }
            .addOnFailureListener {
                Log.e("TestScreen", "Błąd przy dodawaniu ulubionych: ${it.message}")
            }
    }

    fun removeFromFavorites(recipeId: Int) {
        if (userUid == null) {
            Log.w("TestScreen", "Użytkownik nie jest zalogowany")
            return
        }
        db.collection("users")
            .document(userUid)
            .collection("favorites")
            .document(recipeId.toString())
            .delete()
            .addOnSuccessListener {
                Log.d("TestScreen", "Usunięto z ulubionych: $recipeId")
                isFavorite.value = false
            }
            .addOnFailureListener {
                Log.e("TestScreen", "Błąd przy usuwaniu ulubionych: ${it.message}")
            }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, currentRoute = "test_screen")
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F3F9)),
        containerColor = Color(0xFFF4F3F9)
    ) { innerPadding ->
        recipeDetails?.let { recipe ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(recipe.image),
                            contentDescription = recipe.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(12.dp)
                        ) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Wróć", tint = Color.White)
                        }

                        IconButton(
                            onClick = {
                                if (isFavorite.value) {
                                    removeFromFavorites(recipe.id)
                                } else {
                                    val favRecipe = FavoriteRecipe(
                                        id = recipe.id,
                                        title = recipe.title,
                                        image = recipe.image?: ""
                                    )
                                    addToFavorites(favRecipe)
                                }
                            },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Ulubione",
                                tint = if (isFavorite.value) Color.Red else Color.White
                            )
                        }
                    }
                }

                item {
                    Text(
                        text = recipe.title,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }

                item {
                    Row(
                        modifier = Modifier.padding(start = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(Icons.Default.Schedule, contentDescription = null)
                        Text("${recipe.readyInMinutes} min")
                        Icon(Icons.Default.Star, contentDescription = null)
                        Text(String.format("%.2f", recipe.spoonacularScore))
                        Icon(Icons.Default.People, contentDescription = null)
                        Text("${recipe.servings} porcje")
                    }
                }

                item {
                    val steps = remember(recipe) {
                        recipe.analyzedInstructions.flatMap { it.steps }
                    }
                    val currentStep = remember { mutableIntStateOf(0) }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Sposób przygotowania:",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                            )

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                val prevIsEnabled = currentStep.intValue > 0
                                val nextIsEnabled = currentStep.intValue < steps.size - 1

                                IconButton(
                                    onClick = { if (prevIsEnabled) currentStep.intValue-- },
                                    enabled = prevIsEnabled
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Poprzedni krok",
                                        tint = if (prevIsEnabled) MaterialTheme.colorScheme.primary else Color(0xFF929CA8)
                                    )
                                }

                                Text(
                                    text = "${currentStep.intValue + 1}/${steps.size}",
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontSize = 18.sp
                                )

                                IconButton(
                                    onClick = { if (nextIsEnabled) currentStep.intValue++ },
                                    enabled = nextIsEnabled
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowForward,
                                        contentDescription = "Następny krok",
                                        tint = if (nextIsEnabled) MaterialTheme.colorScheme.primary else Color(0xFF929CA8)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = steps.getOrNull(currentStep.intValue)?.step ?: "Brak kroku",
                            color = MaterialTheme.colorScheme.secondary,
                            fontSize = 16.sp,
                            lineHeight = 22.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                        )
                    }
                }

                item {
                    Text(
                        text = "Składniki:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }

                items(recipe.extendedIngredients) { ingredient ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(text = ingredient.name, fontWeight = FontWeight.SemiBold)
                        Text(text = ingredient.original, style = MaterialTheme.typography.bodyMedium)
                        Divider(modifier = Modifier.padding(vertical = 4.dp))
                    }
                }
            }
        } ?: run {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            ) {
                CircularProgressIndicator()
            }
        }
    }
}
