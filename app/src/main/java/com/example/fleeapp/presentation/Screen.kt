package com.example.fleeapp.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector


data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

sealed class Screen(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    object HomepageFeedScreen : Screen("homepage_feed_screen", "Home", Icons.Filled.Home, Icons.Outlined.Home)
    object ListenMusicScreen : Screen("listen_music_screen", "Listen", Icons.Filled.Star, Icons.Outlined.Star)
    object MusicPlayerScreen : Screen("music_player_screen", "Play", Icons.Filled.PlayArrow, Icons.Outlined.PlayArrow)
    object ManagePlaylistsScreen : Screen("manage_playlists_screen", "Manage", Icons.Filled.List, Icons.Outlined.List)
}