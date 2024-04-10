package raf.rma.catalist.core.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import raf.rma.catalist.core.theme.primaryText

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ErrorContainer(
    error: Throwable
) {
    FlowColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = error.message ?: "Unexpected error occurred.",
            color = primaryText
        )
    }

}