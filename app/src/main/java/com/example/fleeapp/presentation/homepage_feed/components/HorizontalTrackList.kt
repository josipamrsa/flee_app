package com.example.fleeapp.presentation.homepage_feed.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fleeapp.domain.model.tracks.Track
import com.example.fleeapp.presentation.common_ui.DataErrorHandler
import com.example.fleeapp.presentation.common_ui.DataLoaderDisplay
import com.example.fleeapp.presentation.common_ui.ListDisplayState
import com.example.fleeapp.presentation.common_ui.theme.flee_main.FleeMainTheme
import com.example.fleeapp.presentation.homepage_feed.states.PreviewTrackState

@Composable
fun Filterable(
    title: String,
    options: Map<String, String>,
    onFilterableClicked: (Map.Entry<String, String>) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    /*var filterTitle by remember {
        mutableStateOf(title)
    }*/

    fun filterData(option: Map.Entry<String, String>) {
        onFilterableClicked(option)
        expanded = false
    }


    Column(modifier = Modifier
        .width(FleeMainTheme.dimensions.smallComponentWidth)
        .clickable { expanded = !expanded })
    {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 30.dp,
                    start = 15.dp,
                    end = 15.dp
                )
                .background(
                    color = FleeMainTheme.colors.backgroundSecondary,
                    shape = RoundedCornerShape(7.dp)
                )

        ) {

            Text(
                text = title,
                style = FleeMainTheme.typography.h6,
                color = FleeMainTheme.colors.textAccentPrimary,
                modifier = Modifier
                    .padding(
                        top = 10.dp,
                        start = 15.dp,
                        end = 5.dp,
                        bottom = 10.dp
                    )
            )

            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Open dropdown menu",
                tint = FleeMainTheme.colors.textAccentPrimary,
                modifier = Modifier.padding(end = 10.dp),
            )
        }


        Box(
            modifier = Modifier
                .padding(
                    top = 5.dp,
                    start = 15.dp
                )
        ) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(FleeMainTheme.colors.backgroundSecondary)
                    .width(FleeMainTheme.dimensions.smallComponentWidth)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = option.value,
                                style = FleeMainTheme.typography.h6,
                            )
                        },
                        onClick = { filterData(option) },
                        colors = MenuDefaults.itemColors(
                            textColor = FleeMainTheme.colors.textAccentSecondary
                        )
                    )
                }

            }
        }
    }
}

@Composable
fun NonFilterable(title: String) {
    Text(
        text = title,
        style = FleeMainTheme.typography.h6,
        color = FleeMainTheme.colors.textAccentPrimary,
        modifier = Modifier.padding(
            top = 30.dp,
            start = 20.dp
        )
    )
}

@Composable
fun HorizontalTrackList(
    title: String,
    trackPlaying: PreviewTrackState<Track>,
    tracks: ListDisplayState<Track>,
    filterableTitle: String,
    onFilterableClicked: (Map.Entry<String, String>) -> Unit,
    onTrackClick: () -> Unit,
    onTrackDoubleClick: (Track) -> Unit,
) {

    if (tracks.error.isNotBlank()) {
        DataErrorHandler(error = tracks.error)
    }

    if (tracks.isLoading) {
        // TODO shimmerlayout
        DataLoaderDisplay()
    }

    else {
        if (tracks.filterable)
            Filterable(
                title = filterableTitle,
                options = tracks.filterableOptions,
                onFilterableClicked = onFilterableClicked
            )
        else NonFilterable(title = title)

        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(tracks.data) { wrapped ->
                wrapped.let { it ->
                    RowTrackItem(
                        track = it,
                        trackPlaying = trackPlaying,
                        onTrackClick = onTrackClick,
                        onTrackDoubleClick = {
                            onTrackDoubleClick(it)
                        }
                    )
                }
            }
        }
    }
}