package com.erazero1.compose_di_example.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.erazero1.compose_di_example.ui.character.CharactersScreen
import com.erazero1.compose_di_example.ui.character_details.CharacterDetailsScreen
import com.erazero1.compose_di_example.ui.location.LocationsScreen
import com.erazero1.compose_di_example.ui.location_details.LocationDetailsScreen

sealed class Screen(val route: String) {
    object Characters : Screen("characters_screen")
    object CharacterDetailsScreen : Screen("character_details_screen")
    object Locations : Screen("locations_screen")
    object LocationDetailsScreen : Screen("location_details_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier,
    onTitleChange: (String) -> Unit
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Characters.route,
        modifier = modifier
    ) {

        composable(route = Screen.Locations.route) {
            onTitleChange("All Locations")
            LocationsScreen(navController)
        }
        composable(route = Screen.Characters.route) {
            onTitleChange("All Characters")
            CharactersScreen(navController)
        }
        composable(
            route = Screen.CharacterDetailsScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { entry ->
            onTitleChange("Character Details")
            CharacterDetailsScreen(id = entry.arguments?.getInt("id") ?: -1)
        }
        composable(
            route = Screen.LocationDetailsScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { entry ->
            onTitleChange("Location Details")
            LocationDetailsScreen(
                id = entry.arguments?.getInt("id") ?: -1
            )
        }
    }


}