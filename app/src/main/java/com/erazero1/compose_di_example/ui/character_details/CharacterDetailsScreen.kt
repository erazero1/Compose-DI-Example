package com.erazero1.compose_di_example.ui.character_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.erazero1.compose_di_example.ui.character.StatusIndicator
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun CharacterDetailsScreen(
    id: Int,
) {
    val viewModel = koinViewModel<CharacterDetailsViewModel> { parametersOf(id) }
    val state by viewModel.uiState.collectAsState()


    when {
        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        id == -1 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Location not found",
                    color = Color.Red,
                    fontSize = 18.sp
                )
            }
        }

        state.errorMessage != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.errorMessage ?: "Error occurred",
                    color = Color.Red,
                    fontSize = 18.sp
                )
            }
        }


        else -> {
            val character = state.character
            val episode = state.episode
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                AsyncImage(
                    model = character.image,
                    contentDescription = "${character.name} avatar",
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .size(400.dp)
                        .clip(RoundedCornerShape(percent = 5))
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = character.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
                Row {
                    StatusIndicator(
                        isAlive = character.status,
                        paddingValues = PaddingValues(start = 16.dp, top = 12.dp)
                    )
                    Text(
                        text = "${character.status} - ${character.species}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W500,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                    )
                }

                Text(
                    text = "${character.gender} ${character.type}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

                Text(
                    text = "Origin: ${character.origin.name}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

                Text(
                    text = "Last seen location: ${character.location.name}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

                Text(
                    text = "First seen in: ${episode.name} episode",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

            }
        }

    }

}