package com.erazero1.compose_di_example.ui.location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.erazero1.compose_di_example.domain.entity.DetailedLocation
import com.erazero1.compose_di_example.ui.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun LocationsScreen(navController: NavHostController) {
    val viewModel = koinViewModel<LocationViewModel>()
    val state by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.errorMessage != null -> {
                Text(
                    text = state.errorMessage ?: "Error",
                    color = Color.Red,
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .background(Color.White)
                    ) {
                        items(state.locations) { location ->
                            LocationCard(location) {
                                navController.navigate(
                                    Screen.LocationDetailsScreen.withArgs(
                                        location.id.toString()
                                    )
                                )
                            }
                        }
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(
                                    onClick = { viewModel.loadPreviousPage() },
                                    enabled = state.page > 1
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                        contentDescription = "Previous Page"
                                    )
                                }

                                Text(
                                    text = "Page ${state.page}",
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )

                                IconButton(
                                    onClick = { viewModel.loadNextPage() },
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
    }
}

@Composable
fun LocationCard(location: DetailedLocation, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Name: ${location.name}", fontSize = 16.sp, color = Color.Black)
            Text(
                text = "Dimension: ${location.dimension}",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
            Text(text = "Type: ${location.type}", fontSize = 14.sp, color = Color.DarkGray)
        }
    }
}