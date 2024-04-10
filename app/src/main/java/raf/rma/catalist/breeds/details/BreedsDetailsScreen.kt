package raf.rma.catalist.breeds.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import raf.rma.catalist.core.compose.Container
import raf.rma.catalist.core.compose.Header
import raf.rma.catalist.core.compose.IndeterminateCircularIndicator


fun NavGraphBuilder.breedsDetailsScreen(
    route: String,
    onBack: () -> Unit,
    onSearch: () -> Unit,
    onWiki: (String) -> Unit,
) = composable(route = route) {
        navBackStackEntry ->
    val dataId = navBackStackEntry.arguments?.getString("id")
        ?: throw IllegalArgumentException("id is required.")


    val viewModel = viewModel<BreedsDetailsViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return BreedsDetailsViewModel(breedId = dataId) as T
            }

        }
    )

    val state by viewModel.state.collectAsState()

    BreedsDetailsScreen(
        state = state,
        onBack = onBack,
        onSearch = onSearch,
        onWiki = onWiki,
    )

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BreedsDetailsScreen(
    state: BreedsDetailsState,
    onBack: () -> Unit,
    onSearch: () -> Unit,
    onWiki: (String) -> Unit,
) {

    Scaffold(
        topBar = {  Header(
            onBack = { onBack() },
            onSearch = { onSearch() }
        )  },
        content = {
            if (state.loading) {
                IndeterminateCircularIndicator()
            } else {
                Container(
                    paddingValues = it
                ) {
                    BreedsDetailsItem(
                        breed = state.breed!!,
                        onWikiClick = { onWiki(state.breed.wikiUrl!!) }
                    )
                }
            }
        }
    )
}