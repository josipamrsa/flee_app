package com.example.fleeapp.presentation.navigation.bottom_navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fleeapp.presentation.Screen

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Screen.HomepageFeedScreen,
        Screen.ListenMusicScreen,
        Screen.MusicPlayerScreen,
        Screen.ManagePlaylistsScreen
    )

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    fun navigateTo(index: Int, screen: Screen) {
        selectedItemIndex = index
        navController.navigate(screen.route)
    }

    NavigationBar() {
        items.forEachIndexed { index, screen ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = { navigateTo(index = index, screen = screen) },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) screen.selectedIcon else screen.unselectedIcon,
                        contentDescription = screen.title
                    )
                },
                label = { Text(text = screen.title) }
            )
        }
    }
}