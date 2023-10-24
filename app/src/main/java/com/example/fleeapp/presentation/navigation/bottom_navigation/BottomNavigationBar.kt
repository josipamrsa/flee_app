package com.example.fleeapp.presentation.navigation.bottom_navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fleeapp.presentation.Screen
import com.example.fleeapp.presentation.base_ui.theme.flee_main.FleeMainTheme

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

    NavigationBar(
        containerColor = FleeMainTheme.colors.backgroundSecondary,
    ) {
        items.forEachIndexed { index, screen ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = { navigateTo(index = index, screen = screen) },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex)
                            screen.selectedIcon
                        else screen.unselectedIcon,

                        contentDescription = screen.title,
                    )
                },
                label = {
                    Text(
                        text = screen.title,
                        style = FleeMainTheme.typography.small
                    )
                },

                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = FleeMainTheme.colors.navItemSelectedIcon,
                    selectedTextColor = FleeMainTheme.colors.navItemSelectedBackground,
                    unselectedIconColor = FleeMainTheme.colors.navItemUnselectedIcon,
                    unselectedTextColor = FleeMainTheme.colors.textSecondary,
                    indicatorColor = FleeMainTheme.colors.navItemSelectedBackground,
                ),
            )
        }
    }
}