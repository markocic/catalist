package raf.rma.catalist.core.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import raf.rma.catalist.core.theme.Typography
import raf.rma.catalist.core.theme.primaryText

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ErrorContainer(
    error: Throwable
) {
    FlowColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 64.dp),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = error.message ?: "Unexpected error occurred.",
            color = primaryText,
            textAlign = TextAlign.Center,
            style = Typography.titleMedium
        )
    }

}