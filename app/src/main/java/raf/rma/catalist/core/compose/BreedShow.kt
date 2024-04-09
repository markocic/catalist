package raf.rma.catalist.core.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import raf.rma.catalist.breeds.model.BreedsModel
import raf.rma.catalist.core.theme.Typography
import raf.rma.catalist.core.theme.accentText
import raf.rma.catalist.core.theme.background600
import raf.rma.catalist.core.theme.mutedText
import raf.rma.catalist.core.theme.outline
import raf.rma.catalist.core.theme.primaryText

@OptIn(ExperimentalLayoutApi::class)
@Composable
@ExperimentalMaterial3Api
fun BreedShow(
    breed: BreedsModel,
    modifier: Modifier,
    onClick: () -> Unit
) {
    val DESCRIPTION_LENGHT_LIMIT = 250
    val TEMPERAMENT_LENGHT_LIMIT = 3

    Surface (
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 0.dp),
        shape = RoundedCornerShape(12.dp)

    ){
        Card(
            colors = CardDefaults.cardColors(
                containerColor = background600,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() },

        ) {
            AsyncImage(model = breed.image?.url, contentDescription = "breed image")
            FlowColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
            ) {
                FlowColumn {
                    Text(
                        text = breed.name,
                        color = primaryText,
                        style = Typography.titleLarge

                    )
                    if (breed.altNames.isNotEmpty()) {
                        Text(
                            text = breed.altNames.joinToString(", "),
                            color = mutedText,
                        )
                    }
                }
                FlowRow (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                ){
                    breed.temperament.take(TEMPERAMENT_LENGHT_LIMIT).forEach {
                        SuggestionChip(
                            onClick = { /*TODO*/ },
                            label = { Text(it, color = primaryText) },
                        )
                    }
                }
                var description = breed.description
                if (breed.description.length > DESCRIPTION_LENGHT_LIMIT) {
                    description = breed.description.take(DESCRIPTION_LENGHT_LIMIT) + "..."
                }

                Text(
                    text = description,
                    style = Typography.bodyMedium,
                    color = mutedText
                )

                FlowRow (
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ){
                    OutlinedButton(
                        onClick = { onClick() },
                        border = BorderStroke(1.dp, outline)

                    ) {
                        Text(text = "more details", color = accentText)

                    }
                }

            }
        }
    }
}