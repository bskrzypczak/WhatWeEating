package com.example.whatweeating.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import com.example.whatweeating.data.Recipe
import com.example.whatweeating.R
import com.example.whatweeating.data.Ingredient
import com.example.whatweeating.ui.viewmodels.SharedViewModel

@Composable
fun PopularRecipes(navController: NavController, viewModel: SharedViewModel) {

    val popularRecipes = listOf(
        Recipe(
            id = 1,
            name = "Spaghetti Carbonara",
            image = painterResource(id = R.drawable.carbonara),
            rating = 4.5,
            numberOfRatings = 1933,
            difficulty = "Średni",
            time = "25 min",
            ingredients = listOf(
                Ingredient("Makaron", "200 g", R.drawable.i_makaron),
                Ingredient("Jajka", "3 szt.", R.drawable.i_eggs),
                Ingredient("Parmezan", "50 g", R.drawable.i_parmezan),
                Ingredient("Boczek", "100 g", R.drawable.i_bacon),
                Ingredient("Pieprz", "do smaku", R.drawable.i_blackpepper)
            ),
            instructions = listOf(
                "W dużym garnku zagotuj osoloną wodę, wrzuć makaron i gotuj al dente według instrukcji na opakowaniu (zwykle 8–10 minut).",
                "W międzyczasie do miski wbij jajka, dodaj starty parmezan i dokładnie wymieszaj – powstanie kremowy sos.",
                "Pokrój boczek w kostkę i podsmaż go na suchej patelni, aż będzie złocisty i chrupiący. Zdejmij z ognia.",
                "Odcedź makaron, zostawiając ok. 1/2 szklanki wody z gotowania. Gorący makaron przełóż na patelnię z boczkiem.",
                "Dodaj jajeczno-serowy sos i szybko wymieszaj, aby jajka się nie ścięły, tylko utworzyły kremową konsystencję. W razie potrzeby dolej trochę wody z makaronu.",
                "Dopraw świeżo mielonym pieprzem i podawaj od razu."
            ),
            youtubeUrl = "https://www.youtube.com/watch?v=3AAdKl1UYZs",
            description = "Klasyk włoskiej kuchni – kremowy sos z jajek, sera i chrupiącego boczku, podany z makaronem al dente. Idealny na szybki, ale efektowny obiad."
        ),
        Recipe(
            id = 2,
            name = "Lasagne Bolognese",
            image = painterResource(id = R.drawable.lasagne),
            rating = 4.8,
            numberOfRatings = 1048,
            difficulty = "Trudny",
            time = "45 min",
            ingredients = listOf(
                //Ingredient("Płaty lasagne", "200 g", R.drawable.ingredient_lasagne),
                //Ingredient("Mięso mielone", "300 g", R.drawable.ingredient_meat),
                //Ingredient("Pomidory", "400 g", R.drawable.ingredient_tomato),
                //Ingredient("Cebula", "1 szt.", R.drawable.ingredient_onion),
                //Ingredient("Ser", "150 g", R.drawable.ingredient_cheese)
            ),
            instructions = listOf(
                "Na patelni podsmaż pokrojoną cebulę, następnie dodaj mięso mielone i smaż, aż się zarumieni.",
                "Dodaj pokrojone pomidory (lub passatę) i gotuj na małym ogniu przez ok. 15 minut, aż sos zgęstnieje. Dopraw solą i pieprzem.",
                "W naczyniu żaroodpornym układaj warstwowo: sos mięsny, płaty lasagne, starty ser – powtarzaj do wyczerpania składników.",
                "Ostatnią warstwę posyp obficie serem i przykryj folią aluminiową.",
                "Piecz w piekarniku nagrzanym do 180°C przez 30 minut, następnie zdejmij folię i dopiecz 10–15 minut, aż ser się zrumieni.",
                "Po upieczeniu odstaw na 5–10 minut przed pokrojeniem."
            ),
            youtubeUrl = "https://www.youtube.com/watch?v=dHVfG7qHqis",
            description = "Warstwowa uczta z mięsnym sosem bolognese, makaronem i ciągnącym się serem. Tradycyjne danie z włoskim sercem – sycące i pełne smaku."
        ),
        Recipe(
            id = 3,
            name = "Ratatouille",
            image = painterResource(id = R.drawable.ratatouille),
            rating = 4.9,
            numberOfRatings = 221,
            difficulty = "Średni",
            time = "35 min",
            ingredients = listOf(
                //Ingredient("Cukinia", "1 szt.", R.drawable.ingredient_zucchini),
                //Ingredient("Bakłażan", "1 szt.", R.drawable.ingredient_eggplant),
                //Ingredient("Papryka", "1 szt.", R.drawable.ingredient_pepper),
                //Ingredient("Pomidor", "3 szt.", R.drawable.ingredient_tomato),
                //Ingredient("Cebula", "1 szt.", R.drawable.ingredient_onion),
                //Ingredient("Czosnek", "2 ząbki", R.drawable.ingredient_garlic)
            ),
            instructions = listOf(
                "Umyj wszystkie warzywa. Pokrój cukinię, bakłażana, pomidory i paprykę w cienkie plastry. Cebulę i czosnek pokrój drobniej.",
                "Na dnie żaroodpornego naczynia rozprowadź cienką warstwę oliwy i wyłóż pokrojoną cebulę oraz czosnek.",
                "Na cebuli układaj na zakładkę pokrojone warzywa – tworząc kolorową spiralę lub rzędy.",
                "Całość posól, popieprz i skrop oliwą z oliwek. Możesz też dodać zioła prowansalskie lub świeży tymianek.",
                "Przykryj naczynie folią aluminiową i wstaw do nagrzanego piekarnika (180°C). Piecz przez 30–35 minut.",
                "Na koniec zdejmij folię i piecz dodatkowe 5–10 minut, aby warzywa się lekko zarumieniły."
            ),
            youtubeUrl = "https://www.youtube.com/watch?v=iCMGPRiDXQg",
            description = "Kolorowa zapiekanka z warzyw – prosto z francuskiej Prowansji. Zdrowa, aromatyczna i zachwycająca wyglądem, jak z bajki o gotującym szczurze."
        ),
        Recipe(
            id = 4,
            name = "Empanadas",
            image = painterResource(id = R.drawable.empanadas),
            rating = 4.8,
            numberOfRatings = 587,
            difficulty = "Łatwy",
            time = "25 min",
            ingredients = listOf(
                //Ingredient("Ciasto", "300 g", R.drawable.ingredient_dough),
                //Ingredient("Mięso mielone", "200 g", R.drawable.ingredient_meat),
                //Ingredient("Cebula", "1 szt.", R.drawable.ingredient_onion),
                //Ingredient("Papryka", "1 szt.", R.drawable.ingredient_pepper),
                //Ingredient("Przyprawy", "do smaku", R.drawable.ingredient_spices)
            ),
            instructions = listOf(
                "Na patelni podsmaż pokrojoną cebulę z mięsem mielonym. Gdy mięso się zarumieni, dodaj pokrojoną paprykę i przyprawy. Smaż, aż wszystko zmięknie.",
                "Odstaw farsz do ostygnięcia. W międzyczasie przygotuj ciasto – rozwałkuj je i wykrój kółka (ok. 10–12 cm średnicy).",
                "Na każdym krążku ciasta ułóż łyżkę farszu, złóż na pół i dociśnij brzegi widelcem, aby dobrze się skleiły.",
                "Empanady możesz posmarować roztrzepanym jajkiem dla złocistego koloru.",
                "Piecz w piekarniku nagrzanym do 200°C przez 20–25 minut lub smaż na oleju do złotego koloru.",
                "Podawaj na ciepło lub zimno – świetnie smakują z salsą lub dipem jogurtowym."
            ),
            youtubeUrl = "https://www.youtube.com/watch?v=LMJ1vGwsVns",
            description = "Chrupiące pierożki z nadzieniem z mięsa i warzyw. Popularna przekąska w Ameryce Południowej, którą można podać na ciepło lub zimno."
        )
    )


    val topRatingRecipes = popularRecipes.sortedByDescending { it.rating }

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
                RecipeCard(popularRecipe){
                    viewModel.selectRecipe(popularRecipe)
                    navController.navigate("recipe_screen")
                }
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
                RecipeCard(topRecipe){
                    viewModel.selectRecipe(topRecipe)
                    navController.navigate("recipe_screen")
                }
            }
        }
    }
}


@Composable
fun RecipeCard(recipe: Recipe, onClick: ()-> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(180.dp)
            .height(180.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEEEAEA),
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant     // kolor tekstu/ikon
        )
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

