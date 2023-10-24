package com.example.fleeapp.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.fleeapp.presentation.base_ui.theme.flee_main.FleeMainTheme
import com.example.fleeapp.presentation.navigation.bottom_navigation.BottomNavigationBar
import com.example.fleeapp.presentation.navigation.components.NavigationGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Flee",
                        color =  FleeMainTheme.colors.textPrimary,
                        style = FleeMainTheme.typography.header
                    )

                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = FleeMainTheme.colors.backgroundSecondary
                )
            )
        },

        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .background(FleeMainTheme.colors.backgroundPrimary)
        ) {
            NavigationGraph(navController = navController)
        }
    }
}