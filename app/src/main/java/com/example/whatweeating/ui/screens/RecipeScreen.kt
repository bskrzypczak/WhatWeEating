package com.example.whatweeating.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.example.whatweeating.data.Ingredient
import com.example.whatweeating.ui.components.BottomNavigationBar
import com.example.whatweeating.ui.theme.AppPrimary
import com.example.whatweeating.ui.viewmodels.SharedViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun RecipeScreen(navController: NavController, viewModel: SharedViewModel) {
    val recipe = viewModel.selectedRecipe.value
    val isFavorite = remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                currentRoute = "recipe_screen"
            )
        },
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFFF4F3F9)),
        containerColor = Color(0xFFF4F3F9)

    ) { innerPadding ->
        if (recipe != null) {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentPadding = PaddingValues(bottom = 16.dp), // tylko dół, bez marginesu na górze/na bokach
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                    ) {
                        Image(
                            painter = recipe.image,
                            contentDescription = recipe.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(0.dp))
                        )

                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(12.dp)
                                .size(36.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Wróć",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }

                        IconButton(
                            onClick = { isFavorite.value = !isFavorite.value },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(12.dp)
                                .size(36.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Ulubione",
                                tint = if (isFavorite.value)
                                    Color(0xFFEDD8BD)
                                else
                                    Color.White
                            )
                        }
                    }
                }

                item {
                    Text(
                        text = recipe.name,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                    )
                }

                item {
                    Text(
                        text = recipe.description,
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                    )
                }

                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = "Czas",
                                tint = MaterialTheme.colorScheme.primary,

                                )
                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = recipe.time,
                                color = MaterialTheme.colorScheme.primary,
                                )

                            Spacer(modifier = Modifier.width(12.dp))
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Ocena",
                                tint = MaterialTheme.colorScheme.primary,
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = "${recipe.rating} (${recipe.numberOfRatings})",
                                color = MaterialTheme.colorScheme.primary,
                            )

                            Spacer(modifier = Modifier.width(12.dp))
                            Icon(
                                imageVector = Icons.Default.Whatshot,
                                contentDescription = "Ocena",
                                tint = MaterialTheme.colorScheme.primary,

                                )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = recipe.difficulty,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    Text(
                        text = "Składniki:",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 2.dp, start = 12.dp)
                    )
                }

                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp)
                    ) {
                        items(recipe.ingredients) { ingredient ->
                            IngredientCard(ingredient = ingredient)
                        }
                    }
                }

                item {
                    val currentStep = remember { mutableIntStateOf(0) }
                    val steps = recipe.instructions

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

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                val prevIsEnabled = currentStep.intValue > 0
                                val nextIsEnabled = currentStep.intValue < steps.size - 1

                                IconButton(
                                    onClick = {
                                        if (prevIsEnabled) currentStep.intValue--
                                              },
                                    enabled = prevIsEnabled
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Poprzedni krok",
                                        tint = if (prevIsEnabled) MaterialTheme.colorScheme.primary else Color(
                                            0xFF929CA8
                                        ) // np. szary

                                    )
                                }

                                Text(
                                    text = "${currentStep.intValue + 1}/${steps.size}",
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontSize = 18.sp
                                )

                                IconButton(
                                    onClick = {
                                        if (nextIsEnabled) currentStep.intValue++
                                    },
                                    enabled = nextIsEnabled
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowForward,
                                        contentDescription = "Następny krok",
                                        tint = if (nextIsEnabled) MaterialTheme.colorScheme.primary else Color(
                                            0xFF929CA8
                                        )
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = steps[currentStep.intValue],
                            color = MaterialTheme.colorScheme.secondary,
                            fontSize = 16.sp,
                            lineHeight = 22.sp,
                            maxLines = 4,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                        )
                    }

                }

                item {
                    Text(
                        text = "Obejrzyj jak przygotować:",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(start = 12.dp)
                    )
                }
                item{
                    val videoId = recipe.youtubeUrl?.let { extractYoutubeVideoId(it) }

                    videoId?.let {
                        YouTubePlayer(
                            youtubeVideoId = it,
                            lifecycleOwner = LocalLifecycleOwner.current
                        )
                    }

                }

            }
        } else {
            Text("Nie znaleziono przepisu.")
        }
    }
}

@Composable
fun YouTubePlayer(
    youtubeVideoId: String,
    lifecycleOwner: LifecycleOwner
){
    AndroidView(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp)
            .clip(RoundedCornerShape(10.dp)),

        factory = {
            YouTubePlayerView(context = it).apply{
                lifecycleOwner.lifecycle.addObserver(this)

                addYouTubePlayerListener(object: AbstractYouTubePlayerListener(){
                    override fun onReady(youTubePlayer: YouTubePlayer){
                        youTubePlayer.cueVideo(youtubeVideoId, 0f)
                    }
                })
            }
        }
    )

}

fun extractYoutubeVideoId(url: String): String? {
    val regex = Regex("""(?:v=|\/)([0-9A-Za-z_-]{11})""")
    return regex.find(url)?.groupValues?.get(1)
}

@Composable
fun IngredientCard(ingredient: Ingredient) {
    Column(
        modifier = Modifier
            .width(115.dp)
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = ingredient.imageResId),
            contentDescription = ingredient.name,
            modifier = Modifier
                .size(width = 115.dp, height = 150.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = ingredient.name,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            maxLines = 1
        )
        Text(
            text = ingredient.quantity,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}


