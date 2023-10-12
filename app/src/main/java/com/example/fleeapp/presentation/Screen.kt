package com.example.fleeapp.presentation

sealed class Screen(val route: String) {
    object HomepageFeedScreen: Screen("homepage_feed_screen")
}