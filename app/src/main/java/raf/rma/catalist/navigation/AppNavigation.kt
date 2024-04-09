package raf.rma.catalist.navigation

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import raf.rma.catalist.breeds.details.breedsDetailsScreen
import raf.rma.catalist.breeds.list.breedsListScreen
import raf.rma.catalist.breeds.search.breedsSearchScreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val uriHandler = LocalUriHandler.current

    fun onBack() {
        navController.navigateUp()
    }
    fun onSearch() {
        navController.navigate("search")
    }
    val onDetails: (String) -> Unit = {
        navController.navigate("breedsdetails/${it}")
    }
    val onWiki: (String) -> Unit = {
        uriHandler.openUri(it)
    }

    NavHost(
        navController = navController,
        startDestination = "breedslist"
    ) {
        breedsListScreen(
            route = "breedslist",
            onBack = ::onBack,
            onSearch = ::onSearch,
            onMoreDetails = onDetails
        )
        breedsDetailsScreen(
            route = "breedsdetails/{id}",
            onBack = ::onBack,
            onSearch = ::onSearch,
            onWiki = onWiki,
        )
        breedsSearchScreen(
            route = "search",
            onBack = ::onBack,
            onSearch = ::onSearch,
            onMoreDetails = onDetails
        )

    }

}