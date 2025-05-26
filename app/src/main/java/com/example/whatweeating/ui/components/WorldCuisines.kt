package com.example.whatweeating.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatweeating.R

@Composable
fun WorldCuisines(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 2.dp)
    ) {
        Text(
            text = "Kuchnie świata",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp, top = 10.dp)
        )
        Row(){
            ContinentCard("Europa", "europe")
            Spacer(modifier = Modifier.width(12.dp))
            ContinentCard("Azja", "asia")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(){
            ContinentCard("Ameryka Południowa", "south_america")
            Spacer(modifier = Modifier.width(12.dp))
            ContinentCard("Ameryka Północna", "north_america")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(){
            ContinentCard("Afryka", "africa")
            Spacer(modifier = Modifier.width(12.dp))

            ContinentCard("Australia i Oceania", "australia")
        }
    }
}

@Composable
fun ContinentCard(name: String, label: String) {
    val imageMap = mapOf(
        "asia" to R.drawable.asia,
        "africa" to R.drawable.africa,
        "north_america" to R.drawable.north_america,
        "south_america" to R.drawable.south_america,
        "europe" to R.drawable.europe,
        "australia" to R.drawable.australia
    )
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(180.dp)
            .height(180.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = imageMap[label] ?: R.drawable.pizza),
                contentDescription = name,
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
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    maxLines = 1
                )
            }
        }
    }
}





