package raf.rma.catalist.breeds.list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import raf.rma.catalist.breeds.domain.BreedInfo
import raf.rma.catalist.core.compose.BreedShow
import raf.rma.catalist.core.compose.Header
import raf.rma.catalist.core.theme.separator

@ExperimentalMaterial3Api
@ExperimentalLayoutApi
fun NavGraphBuilder.breedsListScreen(
    route: String,
    navController: NavController,
) = composable(route = route) {

    val item1 = BreedInfo(
        name = "Abyssinian",
        altNames = "",
        description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        temperament = listOf("Active", "Energetic", "Independent"),
        imageURL = "https://cdn2.thecatapi.com/images/xnzzM6MBI.jpg"
    )
    val item2 = BreedInfo(
        name = "Abyssinian 1",
        altNames = "",
        description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        temperament = listOf("Active", "Energetic", "Independent"),
        imageURL = "https://cdn2.thecatapi.com/images/xnzzM6MBI.jpg"
    )

    val breedsListState = BreedsListState(items = listOf(item1, item2))
    BreedsListScreen(navController, breedsListState)

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
fun BreedsListScreen(
    navController: NavController,
    breedsListState: BreedsListState
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
                    .padding(horizontal = 0.dp, vertical = 24.dp)
            ) {
                BreedShow(breedInfo = breedsListState.items[0], modifier = Modifier.padding(it))
                Divider(Modifier.fillMaxWidth(), color = separator)
                BreedShow(breedInfo = breedsListState.items[0], modifier = Modifier.padding(0.dp))
                Divider(Modifier.fillMaxWidth(), color = separator)
                BreedShow(breedInfo = breedsListState.items[0], modifier = Modifier.padding(0.dp))
            }
        }
    )


}