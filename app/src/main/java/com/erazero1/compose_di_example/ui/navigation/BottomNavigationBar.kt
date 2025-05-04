package com.erazero1.compose_di_example.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {

    val navigationItems = listOf(
        NavigationItem(
            title = "Characters",
            icon = Icons.Default.Person,
            route = Screen.Characters.route
        ),
        NavigationItem(
            title = "Locations",
            icon = Icons.Default.LocationOn,
            route = Screen.Locations.route
        )
    )

    val selectedNavItemIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar(containerColor = Color.White) {

        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavItemIndex.intValue == index,
                onClick = {
                    selectedNavItemIndex.intValue = index
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                },
                label = {
                    Text(
                        item.title,
                        color = if (index == selectedNavItemIndex.intValue)
                            Color.Black
                        else Color.Gray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)
