package raf.rma.catalist.breeds.search

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import raf.rma.catalist.breeds.details.BreedsDetailsItem
import raf.rma.catalist.breeds.list.BreedShow
import raf.rma.catalist.core.compose.Header
import raf.rma.catalist.core.theme.background600
import raf.rma.catalist.core.theme.mutedText
import raf.rma.catalist.core.theme.primaryText
import raf.rma.catalist.core.theme.separator

fun NavGraphBuilder.breedsSearchScreen(
    route: String,
    navController: NavController,
) = composable(route = route) {

    val viewModel = viewModel<BreedsSearchViewModel>()
    val state by viewModel.state.collectAsState()

    BreedsSearchScreen(
        state = state,
        navController = navController,
        search =  viewModel::filter
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BreedsSearchScreen(
    state: BreedsSearchState,
    navController: NavController,
    search: (String) -> Unit
) {

    Scaffold(
        topBar = {  Header(
            onBack = { navController.navigateUp() },
            onSearch = { navController.navigate("search") }

        )  },
        content = {
            FlowColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(it)
                    .padding(horizontal = 0.dp, vertical = 24.dp)
            ) {
                var text by rememberSaveable { mutableStateOf("") }
                fun test(q: String) {
                    println(q)
                }
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    colors = SearchBarDefaults.colors(
                        containerColor = background600,
                        inputFieldColors = SearchBarDefaults.inputFieldColors(
                            focusedTextColor = primaryText,
                            unfocusedTextColor = primaryText
                        )
                    ),
                    query = text,
                    onSearch = { search(text) },
                    active = false,
                    content = {},
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "search icon",
                            tint = primaryText
                        )
                    },
                    onActiveChange = {},
                    onQueryChange = { text = it },
                    placeholder = { Text("Search...", color = mutedText) }

                )
                Spacer(modifier = Modifier.padding(vertical = 3.dp))
                state.results.forEach {breed ->
                    BreedShow(
                        breed = breed,
                        modifier = Modifier.padding(0.dp),
                        onClick = { navController.navigate("breedsdetails/${breed.id}") }
                    )
                    HorizontalDivider(Modifier.fillMaxWidth(), color = separator)
                }
            }
        }
    )
}
