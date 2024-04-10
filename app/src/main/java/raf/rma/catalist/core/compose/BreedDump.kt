package raf.rma.catalist.core.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import raf.rma.catalist.breeds.model.BreedsModel
import raf.rma.catalist.core.theme.separator


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@NonRestartableComposable
fun BreedDump(
    breeds: List<BreedsModel>,
    onMoreDetails: (String) -> Unit
) {
    breeds.forEach {breed ->
        BreedItem(
            breed = breed,
            modifier = Modifier.padding(0.dp),
            onClick = { onMoreDetails(breed.id) }
        )
        HorizontalDivider(Modifier.fillMaxWidth(), color = separator)
    }

}