package raf.rma.catalist.breeds.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import raf.rma.catalist.breeds.model.BreedsModel
import raf.rma.catalist.core.theme.Typography
import raf.rma.catalist.core.theme.accentText
import raf.rma.catalist.core.theme.mutedText
import raf.rma.catalist.core.theme.primaryText

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BreedsDetailsItem(
    breed: BreedsModel,
    onWikiClick: () -> Unit,
) {
    FlowColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
    ) {
        FlowColumn (
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
        ) {
            Text(
                breed.name,
                style = Typography.headlineLarge
                    .copy(
                        fontWeight = FontWeight.Bold,
                        color = accentText
                    ),
                modifier = Modifier
            )
            Text(
                breed.altNames.joinToString(", "),
                style = Typography.headlineSmall
                    .copy(
                        color = mutedText
                    ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        FlowRow (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = "https://cdn2.thecatapi.com/images/${breed.imageId}.jpg",
                contentDescription = "image",
                modifier = Modifier.fillMaxWidth()

            )
        }
        FlowColumn (
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            modifier = Modifier
                .background(Color.Transparent)
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
        ) {
            Text(
                "Country origin: ${breed.country}",
                style = Typography.headlineSmall.copy(color = primaryText),
            )
            FlowRow (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            ){
                breed.temperament.forEach {
                    SuggestionChip(
                        onClick = { /*TODO*/ },
                        label = { Text(it, color = primaryText) },
                    )
                }
            }
            Text(
                breed.description,
                style = Typography.bodyLarge.copy(color = primaryText),
            )
            FlowColumn (
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                modifier = Modifier.fillMaxWidth(),
            ){
                RangeIndicator(label = "Adaptability", value = breed.adaptability)
                RangeIndicator(label = "Affection Level", value = breed.affectionLevel)
                RangeIndicator(label = "Child Friendly", value = breed.childFriendly)
                RangeIndicator(label = "Energy Level", value = breed.energyLevel)
                RangeIndicator(label = "Grooming", value = breed.grooming)
            }
            Text(
                "Life span: from ${breed.lifeSpan.from} to ${breed.lifeSpan.to} years",
                style = Typography.bodyLarge.copy(color = primaryText),
            )
            Text(
                "Weight: from ${breed.weight.metric.from} kg to ${breed.weight.metric.to} kg",
                style = Typography.bodyLarge.copy(color = primaryText),
            )
            Text(
                "Rare: ${breed.isRare}",
                style = Typography.bodyLarge.copy(color = primaryText),
            )
            FlowRow (
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ){
                TextButton(
                    onClick =  { onWikiClick() },
                ) {
                    Text(text = "read on wikipedia", color = accentText)

                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RangeIndicator(label: String, value: Int) {
    var sliderPosition by remember { mutableFloatStateOf(value.toFloat()) }
    FlowColumn(
        verticalArrangement = Arrangement.spacedBy((-12).dp, Alignment.Top),
    ) {
        Text(label, color = primaryText)
        Slider(
            enabled = false,
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            colors = SliderDefaults.colors(
                disabledThumbColor = accentText,
                disabledActiveTrackColor = accentText,
                disabledInactiveTrackColor = primaryText,
            ),
            steps = 3,
            valueRange = 1f..5f
        )
    }
}
