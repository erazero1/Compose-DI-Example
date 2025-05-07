package com.erazero1.compose_di_example.ui.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.erazero1.compose_di_example.domain.entity.Character
import com.erazero1.compose_di_example.ui.navigation.Screen


@Composable
fun CharactersScreen(navController: NavController) {
    val viewModel = hiltViewModel<CharacterViewModel>()
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

        state.errorMessage != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.errorMessage ?: "Error",
                    color = Color.Red,
                    fontSize = 18.sp
                )
            }
        }

        else -> {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(state.characters) { character ->
                    CharacterCard(character = character) {
                        navController
                            .navigate(Screen.CharacterDetailsScreen.withArgs(character.id.toString()))
                    }
                }
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            onClick = {
                                if (state.page > 1) {
                                    viewModel.loadPrevious()
                                }
                            },
                            enabled = state.page > 1
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = "Previous Page"
                            )
                        }

                        Text(
                            text = "Page ${state.page}",
                            modifier = Modifier.align(Alignment.CenterVertically),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        IconButton(
                            onClick = {
                                viewModel.loadNext()
                            },
                            enabled = state.hasMorePages
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = "Next Page"
                            )
                        }
                    }
                }
            }
        }
    }

}


@Composable
fun CharacterCard(character: Character, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .height(360.dp)
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE0F7FA)
        ),
        onClick = onClick
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = "${character.name} avatar",
            modifier = Modifier
                .height(190.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )
        Text(
            text = character.name,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(4.dp),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Row {
            StatusIndicator(
                isAlive = character.status,
                paddingValues = PaddingValues(horizontal = 4.dp, vertical = 8.dp)
            )
            Text(
                text = "${character.status} - ${character.species}",
                modifier = Modifier
                    .padding(4.dp),
                color = Color.DarkGray,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Text(
            text = "Last seen location:",
            fontSize = 16.sp,
            color = Color.DarkGray,
            modifier = Modifier
                .padding(horizontal = 4.dp)
        )
        Text(
            text = character.location.name,
            fontSize = 16.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(horizontal = 4.dp)
        )

    }
}


@Composable
fun StatusIndicator(isAlive: String, paddingValues: PaddingValues) {
    var color = Color.Red
    when (isAlive) {
        "Alive" -> color = Color.Green
        "Dead" -> color = Color.Red
        "unknown" -> color = Color.Gray
    }

    Box(
        modifier = Modifier
            .padding(paddingValues)
            .size(16.dp)
            .background(color = color, shape = CircleShape)
    )
}

