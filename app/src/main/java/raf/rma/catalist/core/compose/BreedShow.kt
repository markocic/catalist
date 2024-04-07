package raf.rma.catalist.core.compose

import android.graphics.Insets
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ChipBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import raf.rma.catalist.breeds.domain.BreedInfo
import raf.rma.catalist.core.theme.Typography
import raf.rma.catalist.core.theme.accentText
import raf.rma.catalist.core.theme.background600
import raf.rma.catalist.core.theme.mutedText
import raf.rma.catalist.core.theme.outline
import raf.rma.catalist.core.theme.primaryText
import raf.rma.catalist.core.theme.separator

@OptIn(ExperimentalLayoutApi::class)
@Composable
@ExperimentalMaterial3Api
fun BreedShow(breedInfo: BreedInfo, modifier: Modifier) {

    Surface (
        modifier = modifier.padding(horizontal = 24.dp, vertical = 0.dp)
    ){
        Card(
            colors = CardDefaults.cardColors(
                containerColor = background600,
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(model = breedInfo.imageURL, contentDescription = "breed image")
            FlowColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
            ) {
                FlowColumn {
                    Text(
                        text = breedInfo.name,
                        color = primaryText,
                        style = Typography.titleLarge

                    )
                    Text(
                        text = breedInfo.name,
                        color = mutedText,
                    )
                }
                FlowRow (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                ){
                    breedInfo.temperament.forEach {
                        SuggestionChip(
                            onClick = { /*TODO*/ },
                            label = { Text(it, color = primaryText) },
                        )
                    }
                }

                Text(
                    text = breedInfo.description,
                    style = Typography.bodyMedium,
                    color = mutedText
                )

                FlowRow (
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ){
                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        border = BorderStroke(1.dp, outline)

                    ) {
                        Text(text = "more details", color = accentText)

                    }
                }

            }
        }
    }
}