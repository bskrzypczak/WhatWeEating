package com.example.whatweeating.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.whatweeating.ui.components.BottomNavigationBar
import com.example.whatweeating.ui.components.ProfileInfo
import com.example.whatweeating.R
import com.example.whatweeating.ui.components.SettingsList
import com.example.whatweeating.ui.viewmodels.AuthState
import com.example.whatweeating.ui.viewmodels.AuthViewModel


@Composable
fun ProfileScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val authState by authViewModel.authState.observeAsState()
    val image = painterResource(id = R.drawable.kot)
    val currentRoute = "profile_screen"

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

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (authState) {
                is AuthState.Authenticated -> {
                    val email = authViewModel.currentUserEmail ?: "Brak e-maila"
                    val name by authViewModel.userName.observeAsState()
                    ProfileInfo(image, email = email, name = name ?: "")
                    SettingsList()
                    Button(
                        onClick = { authViewModel.signout() },
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Icon(Icons.Default.Logout, contentDescription = "Wyloguj się")
                        Text(text = "Wyloguj się", modifier = Modifier.padding(start = 8.dp))
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
                                .size(222.dp)
                                .padding(bottom = 24.dp)
                        )
                        Button(
                            onClick = { navController.navigate("login_screen") },
                            modifier = Modifier
                                .padding(top = 16.dp)
                        ) {
                            Icon(Icons.Default.Login, contentDescription = "Zaloguj się")
                            Text(
                                text = "Zaloguj się, żeby zobaczyć swój profil",
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }

                is AuthState.Loading -> {
                    Text("Ładowanie...")
                }
                is AuthState.Error -> {
                    Text("Błąd: ${(authState as AuthState.Error).message}")
                }
                null -> {
                    Text("Sprawdzanie statusu logowania...")
                }
            }
        }
    }
}
