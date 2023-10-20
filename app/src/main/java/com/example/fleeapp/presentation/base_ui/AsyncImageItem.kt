package com.example.fleeapp.presentation.base_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.fleeapp.R
import com.example.fleeapp.presentation.homepage_feed.components.ComponentWidth

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
    //var painterState by remember { mutableStateOf(initialState) }

    val context = LocalContext.current

    AsyncImage(
        model = imageUrl, /* TODO */
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        /* TODO */
    )
}

