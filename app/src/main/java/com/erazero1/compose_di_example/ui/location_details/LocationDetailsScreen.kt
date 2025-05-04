package com.erazero1.compose_di_example.ui.location_details

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
fun LocationDetailsScreen(
    id: Int
) {
    val viewModel = koinViewModel<LocationDetailsViewModel> { parametersOf(id) }
    val state by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading && state.location == null) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            return@Box
        }

        if (id == -1) {
            Text(
                text = "Location not found",
                color = Color.Red,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.Center)
            )
            return@Box
        }

        state.errorMessage?.let { err ->
            Text(
                text = err,
                color = Color.Red,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.Center)
            )
            return@Box
        }



        state.location.let { loc ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(text = loc.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Dimension: ${loc.dimension}", fontSize = 20.sp)
                Text(text = "Type: ${loc.type}", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Residents:", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))

                if (state.residents.isEmpty()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else {
                    Row(
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState())
                            .fillMaxWidth()
                    ) {
                        state.residents.forEach { char ->
                            Card(
                                modifier = Modifier
                                    .size(width = 240.dp, height = 340.dp)
                                    .padding(end = 8.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    AsyncImage(
                                        model = char.image,
                                        contentDescription = char.name,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(240.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color.LightGray, RoundedCornerShape(50))
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = char.name,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 24.sp
                                    )
                                    Row {
                                        StatusIndicator(char.status, PaddingValues(top = 4.dp, end = 4.dp))
                                        Text(
                                            text = "${char.status} - ${char.species}",
                                            fontSize = 20.sp
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
}