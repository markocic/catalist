package raf.rma.catalist.breeds.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import raf.rma.catalist.core.compose.BreedDump
import raf.rma.catalist.core.compose.Container
import raf.rma.catalist.core.compose.ErrorContainer
import raf.rma.catalist.core.compose.Header
import raf.rma.catalist.core.compose.IndeterminateCircularIndicator

@ExperimentalMaterial3Api
@ExperimentalLayoutApi
fun NavGraphBuilder.breedsListScreen(
    route: String,
    onBack: () -> Unit,
    onSearch: () -> Unit,
    onMoreDetails: (String) -> Unit,
) = composable(route = route) {

    val viewModel = viewModel<BreedsListViewModel>()
    val state by viewModel.state.collectAsState()

    BreedsListScreen(
        onBack = onBack,
        onSearch = onSearch,
        onMoreDetails = onMoreDetails,
        state = state
    )

}

@Composable
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
fun BreedsListScreen(
    onBack: () -> Unit,
    onSearch: () -> Unit,
    onMoreDetails: (String) -> Unit,
    state: BreedsListState
) {
    Scaffold(
        topBar = {  Header(
            onBack = { onBack() },
            onSearch = { onSearch() }
        )  }
    ) {
        if (state.loading) {
            IndeterminateCircularIndicator()
        } else if (state.error != null) {
            ErrorContainer(error = state.error)
        }
        else {
            Container(paddingValues = it) {
                BreedDump(
                    breeds = state.items,
                    onMoreDetails = onMoreDetails
                )
            }
        }
    }
}
