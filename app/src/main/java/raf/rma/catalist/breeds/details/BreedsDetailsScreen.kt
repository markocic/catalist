package raf.rma.catalist.breeds.details

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import raf.rma.catalist.breeds.list.BreedsListViewModel
import raf.rma.catalist.breeds.repository.BreedsRepository
import raf.rma.catalist.core.compose.BreedShow
import raf.rma.catalist.core.compose.Header
import raf.rma.catalist.core.theme.primaryText
import raf.rma.catalist.core.theme.separator


fun NavGraphBuilder.breedsDetailsScreen(
    route: String,
    navController: NavController,
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
        navController = navController
    )

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BreedsDetailsScreen(
    state: BreedsDetailsState,
    navController: NavController,
) {

    Scaffold(
        topBar = {  Header(
            onBack = { navController.navigateUp() },
            onSearch = { Log.println(Log.DEBUG, "TESTING", "search") }

        )  },
        content = {
            FlowColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(it)
                    .padding(horizontal = 0.dp, vertical = 24.dp)
            ) {
                if (state.breed != null) {
                    BreedsDetailsItem(breed = state.breed)
                }
            }
        }
    )
}