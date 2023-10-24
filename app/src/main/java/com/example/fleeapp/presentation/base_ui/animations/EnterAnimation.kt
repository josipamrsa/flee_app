package com.example.fleeapp.presentation.base_ui.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SlideAnimation(
    content: @Composable () -> Unit
) {

    fun onEnter() : EnterTransition =
        slideInHorizontally() +
                expandHorizontally()

    fun onExit() : ExitTransition =
        slideOutHorizontally() +
                shrinkHorizontally(shrinkTowards = Alignment.Start)

    AnimatedVisibility(
        visibleState = MutableTransitionState(initialState = false)
            .apply { targetState = true },
        modifier = Modifier,
        enter = onEnter(),
        exit = onExit()
    ) {
        content()
    }
}