package raf.rma.catalist.breeds.list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import raf.rma.catalist.core.compose.BreedShow
import raf.rma.catalist.core.compose.Header
import raf.rma.catalist.core.compose.IndeterminateCircularIndicator
import raf.rma.catalist.core.theme.primaryText
import raf.rma.catalist.core.theme.separator

@ExperimentalMaterial3Api
@ExperimentalLayoutApi
fun NavGraphBuilder.breedsListScreen(
    route: String,
    navController: NavController,
) = composable(route = route) {

    val viewModel = viewModel<BreedsListViewModel>()
    val state by viewModel.state.collectAsState()

    BreedsListScreen(navController, state)

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
fun BreedsListScreen(
    navController: NavController,
    state: BreedsListState
) {
    Scaffold(
        topBar = {  Header(
            onBack = { navController.navigateUp() },
            onSearch = { Log.println(Log.DEBUG, "TESTING", "search") }

        )  }
    ) {
        FlowColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(vertical = 24.dp)
        ) {
            if (state.loading) {
                FlowColumn (
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth().fillMaxHeight()

                ) {
                    IndeterminateCircularIndicator()
                }
            } else {
                state.items.forEach {item ->
                    BreedShow(
                        breed = item,
                        modifier = Modifier.padding(0.dp),
                        onClick = { navController.navigate("breedsdetails/${item.id}") })
                    HorizontalDivider(Modifier.fillMaxWidth(), color = separator)
                }
            }
        }
    }


}