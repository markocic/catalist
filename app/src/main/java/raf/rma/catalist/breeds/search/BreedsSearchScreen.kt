package raf.rma.catalist.breeds.search

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import raf.rma.catalist.core.compose.BreedDump
import raf.rma.catalist.core.compose.Container
import raf.rma.catalist.core.compose.ErrorContainer
import raf.rma.catalist.core.compose.Header
import raf.rma.catalist.core.compose.IndeterminateCircularIndicator
import raf.rma.catalist.core.theme.background600
import raf.rma.catalist.core.theme.mutedText
import raf.rma.catalist.core.theme.primaryText

fun NavGraphBuilder.breedsSearchScreen(
    route: String,
    onBack: () -> Unit,
    onSearch: () -> Unit,
    onMoreDetails: (String) -> Unit,
) = composable(route = route) {

    val viewModel = viewModel<BreedsSearchViewModel>()
    val state by viewModel.state.collectAsState()

    BreedsSearchScreen(
        state = state,
        onBack = onBack,
        onSearch = onSearch,
        onMoreDetails = onMoreDetails,
        eventPublisher = viewModel::setEvent,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BreedsSearchScreen(
    state: BreedsSearchState,
    onBack: () -> Unit,
    onSearch: () -> Unit,
    onMoreDetails: (String) -> Unit,
    eventPublisher: (SearchUiEvent) -> Unit,
) {

    Scaffold(
        topBar = {  Header(
            onBack = onBack,
            onSearch = onSearch
        )  },
        content = {
            if (state.loading) {
                IndeterminateCircularIndicator()
            }
            if (state.error != null) {
                ErrorContainer(error = state.error)
            }
            else {
                Container(paddingValues = it) {
                    Search(eventPublisher)
                    Spacer(modifier = Modifier.padding(vertical = 3.dp))
                    if (state.results.isEmpty()) {
                        Text(
                            text = "No results found.",
                            color = mutedText,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        BreedDump(
                            breeds = state.results,
                            onMoreDetails = onMoreDetails
                        )
                    }
                }
            }
        }
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun Search(eventPublisher: (SearchUiEvent) -> Unit) {
    var text by rememberSaveable { mutableStateOf("") }
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
        onSearch = {
            eventPublisher(
                SearchUiEvent.SearchSubmitted(term = text)
            )
        },
        active = false,
        content = {},
        trailingIcon = {
            IconButton(
                onClick = {
                    eventPublisher(
                        SearchUiEvent.SearchSubmitted(term = text)
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "search icon",
                    tint = primaryText
                )

            }
        },
        onActiveChange = {},
        onQueryChange = { text = it },
        placeholder = { Text("Search...", color = mutedText) }
    )
}
