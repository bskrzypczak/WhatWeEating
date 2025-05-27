package com.example.whatweeating.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GreetingHeader(name: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 6.dp, start = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            modifier = modifier
                .padding(top = 3.dp),
            text = "Cześć, $name!",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "Powiadomienia",
            modifier = Modifier
                .size(21.dp)
                .clickable {
                    Log.d("App", "Przejdz do powiadomien")
                },
            tint = MaterialTheme.colorScheme.primary

        )

        Spacer(modifier = Modifier.width(6.dp))

        Icon(
            imageVector = Icons.Outlined.ShoppingCart,
            contentDescription = "Lista zakupow",
            modifier = Modifier
                .size(21.dp)
                .clickable {
                    Log.d("App", "Przejdz do listy zakupow")
                },
            tint = MaterialTheme.colorScheme.primary

        )

        Spacer(modifier = Modifier.width(15.dp))
    }
}