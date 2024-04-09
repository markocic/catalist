package raf.rma.catalist.core.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import raf.rma.catalist.R
import raf.rma.catalist.core.theme.onBackground
import raf.rma.catalist.core.theme.primaryText

@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Composable
fun Header(
    onBack: () -> Unit,
    onSearch: () -> Unit,
) {
    FlowColumn(
        maxItemsInEachColumn = 1,
        modifier = Modifier
            .fillMaxWidth()
            .background(onBackground)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.Center,
    ) {
            IconButton(onClick = { onBack() }) {
                Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back",
                        tint = primaryText,
                        modifier = Modifier.size(24.dp),
                    )
            }
            Image(
                painter = painterResource(id = R.drawable.catalist_logo),
                contentDescription = "Catalist logo",
                modifier = Modifier
                    .size(40.dp)
            )
            IconButton(onClick = { onSearch() }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "search",
                    tint = primaryText,
                    modifier = Modifier.size(24.dp)
                )
        }

    }
}