package com.example.fleeapp.presentation.navigation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fleeapp.presentation.Screen
import com.example.fleeapp.presentation.homepage_feed.HomepageFeedScreen
import com.example.fleeapp.presentation.music_player.MusicPlayerScreen
import com.example.fleeapp.presentation.playlist_manager.PlaylistManagerScreen
import com.example.fleeapp.presentation.track_discovery.TrackDiscoveryScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomepageFeedScreen.route
    ) {
        composable(route = Screen.HomepageFeedScreen.route) {
            HomepageFeedScreen(navController = navController)
        }

        composable(route = Screen.ListenMusicScreen.route) {
            TrackDiscoveryScreen(navController = navController)
        }

        composable(route = Screen.MusicPlayerScreen.route) {
            MusicPlayerScreen(navController = navController)
        }

        composable(route = Screen.ManagePlaylistsScreen.route) {
            PlaylistManagerScreen(navController = navController)
        }
    }

}