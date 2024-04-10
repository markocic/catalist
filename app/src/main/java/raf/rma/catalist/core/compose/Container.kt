package raf.rma.catalist.core.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
@OptIn(ExperimentalLayoutApi::class)
fun Container(
    paddingValues: PaddingValues?,
    content: @Composable() (FlowColumnScope.() -> Unit)
) {
    FlowColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(paddingValues ?: PaddingValues(0.dp))
            .padding(horizontal = 0.dp, vertical = 24.dp)
    ) {
        content()
    }

}