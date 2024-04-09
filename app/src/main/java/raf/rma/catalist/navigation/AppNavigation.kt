package raf.rma.catalist.navigation

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import raf.rma.catalist.breeds.details.breedsDetailsScreen
import raf.rma.catalist.breeds.list.breedsListScreen
import raf.rma.catalist.breeds.search.breedsSearchScreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "breedslist"
    ) {
        breedsListScreen(
            route = "breedslist",
            navController = navController
        )
        breedsDetailsScreen(
            route = "breedsdetails/{id}",
            navController = navController
        )
        breedsSearchScreen(
            route = "search",
            navController = navController
        )

    }

}