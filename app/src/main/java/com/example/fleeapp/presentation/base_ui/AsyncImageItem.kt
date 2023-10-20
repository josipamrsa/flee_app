package com.example.fleeapp.presentation.base_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.fleeapp.R

@Composable
fun AsyncImageSimpleItem(
    imageUrl: String,
    contentDescription: String,
    contentScale: ContentScale = ContentScale.Fit,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
    )
}

@Composable
fun AsyncImageItem(
    model: ImageRequest,
    modifier: Modifier = Modifier,
    contentDescription: String,
    placeholder: @Composable BoxScope.() -> Unit = {
        Image(
            painter = painterResource(
                id = R.drawable.ic_flee_track_image_placeholder
            ),
            contentScale = ContentScale.Crop,
            contentDescription = contentDescription ?: "Placeholder"
        )
    },
    loading: @Composable BoxScope.() -> Unit = {
        CircularProgressIndicator()
    },
    initialState: AsyncImagePainter.State = AsyncImagePainter.State.Empty,
    contentScale: ContentScale = ContentScale.Inside
) {
    var painterState by remember {
        mutableStateOf(initialState)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        AsyncImage(
            model = model,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            onState = { painterState = it },
            contentScale = contentScale,
            alignment = Alignment.TopStart,
        )

        when (painterState) {
            is AsyncImagePainter.State.Loading -> loading()
            is AsyncImagePainter.State.Error -> placeholder()
            else -> {}
        }
    }
}

@Composable
fun AsyncAdjustableImageItem(
    imageUrl: String,
    contentDescription: String,
    size: Size = Size.ORIGINAL,
    modifier: Modifier = Modifier,
    placeholder: @Composable BoxScope.() -> Unit = {
        Image(
            painter = painterResource(
                id = R.drawable.ic_flee_track_image_placeholder
            ),
            contentScale = ContentScale.Crop,
            contentDescription = contentDescription ?: "Placeholder"
        )
    },
    loading: @Composable BoxScope.() -> Unit = {
        CircularProgressIndicator()
    },
    initialState: AsyncImagePainter.State = AsyncImagePainter.State.Empty,
    contentScale: ContentScale = ContentScale.Fit
) {
    val context = LocalContext.current

    val model = ImageRequest.Builder(context)
        .data(imageUrl)
        .size(size = size)
        .build()

    AsyncImageItem(
        model = model,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        placeholder = placeholder,
        loading = loading,
        initialState = initialState
    )
}

