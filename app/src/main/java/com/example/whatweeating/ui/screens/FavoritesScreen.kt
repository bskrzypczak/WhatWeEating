package com.example.whatweeating.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Login
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.whatweeating.ui.components.BottomNavigationBar
import com.example.whatweeating.ui.viewmodels.AuthState
import com.example.whatweeating.ui.viewmodels.AuthViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.example.whatweeating.data.FavoriteRecipe

@Composable
fun FavoritesScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val authState by authViewModel.authState.observeAsState()
    val currentRoute = "favorites_screen"
    val db = FirebaseFirestore.getInstance()

    var favorites by remember { mutableStateOf<List<FavoriteRecipe>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(authState) {
        if (authState is AuthState.Authenticated) {
            val uid = authViewModel.currentUserUid
            if (uid != null) {
                loading = true
                errorMessage = null
                db.collection("users")
                    .document(uid)
                    .collection("favorites")
                    .get()
                    .addOnSuccessListener { snapshot ->
                        favorites = snapshot.documents.mapNotNull { doc ->
                            try {
                                val idLong = doc.getLong("id")
                                val idInt = idLong?.toInt() ?: run {
                                    println("Błąd parsowania id (null)")
                                    return@mapNotNull null
                                }
                                val title = doc.getString("title") ?: ""
                                val image = doc.getString("image") ?: ""
                                FavoriteRecipe(id = idInt, title = title, image = image)
                            } catch (e: Exception) {
                                println("Błąd mapowania dokumentu: ${e.message}")
                                null
                            }
                        }
                        loading = false
                        println("Załadowano ulubione przepisy: ${favorites.size}")
                    }
                    .addOnFailureListener { e ->
                        errorMessage = e.localizedMessage
                        loading = false
                        println("Błąd pobierania ulubionych: $errorMessage")
                    }
            }
        } else {
            favorites = emptyList()
        }
    }


    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, currentRoute = currentRoute)
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (authState) {
                is AuthState.Authenticated -> {
                    Text(
                        text = "Ulubione przepisy",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(12.dp)
                    )

                    when {
                        loading -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        errorMessage != null -> {
                            Text(
                                text = "Błąd: $errorMessage",
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                        favorites.isEmpty() -> {
                            Text(
                                text = "Brak ulubionych przepisów",
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                        else -> {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(favorites) { recipe ->
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp, vertical = 8.dp)
                                            .clickable {
                                                navController.navigate("test_screen/${recipe.id}")
                                            },
                                        elevation = CardDefaults.cardElevation(6.dp),
                                        shape = MaterialTheme.shapes.medium // ładnie zaokrąglone rogi
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .padding(16.dp)
                                        ) {
                                            AsyncImage(
                                                model = recipe.image,
                                                contentDescription = recipe.title,
                                                modifier = Modifier
                                                    .size(96.dp)
                                                    .clip(MaterialTheme.shapes.small)
                                            )
                                            Spacer(modifier = Modifier.width(20.dp))
                                            Column(
                                                modifier = Modifier.weight(1f)
                                            ) {
                                                Text(
                                                    text = recipe.title,
                                                    style = MaterialTheme.typography.titleLarge,
                                                    maxLines = 2,
                                                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                                                )
                                                Spacer(modifier = Modifier.height(6.dp))
                                                // Możesz tu dodać dodatkowe info np. kategorię, opis itp.
                                            }
                                            Icon(
                                                imageVector = Icons.Default.Favorite,
                                                contentDescription = "Ulubiony",
                                                tint = MaterialTheme.colorScheme.primary,
                                                modifier = Modifier.size(28.dp)
                                            )
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
                is AuthState.Unauthenticated -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 128.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Ikona konta",
                            modifier = Modifier
                                .size(150.dp)
                                .padding(bottom = 24.dp)
                        )
                        Button(
                            onClick = { navController.navigate("login_screen") }
                        ) {
                            Icon(Icons.Default.Login, contentDescription = "Zaloguj się")
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Zaloguj się, aby zobaczyć ulubione przepisy")
                        }
                    }
                }
                is AuthState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is AuthState.Error -> {
                    Text(
                        text = "Błąd: ${(authState as AuthState.Error).message}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(12.dp)
                    )
                }
                null -> {
                    Text(
                        text = "Sprawdzanie statusu logowania...",
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }
}

