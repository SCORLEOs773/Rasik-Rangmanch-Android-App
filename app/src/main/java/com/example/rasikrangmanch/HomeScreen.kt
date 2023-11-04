package com.example.rasikrangmanch

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController)
{
    Column {
        TopNavigationPanel(navController)
        UpperPanel(navController)
        FeaturedPanel()
    }
}