package com.example.dynamicwidgets.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dynamicwidgets.data.model.ListWidgetState
import com.example.dynamicwidgets.viewmodel.ListWidgetViewModel

@Composable
fun ListWidget(instanceId: String) {
    //Screen dimensions for responsive design
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    val viewModel: ListWidgetViewModel = viewModel(key = instanceId)
    val state by viewModel.state.collectAsState()

    LaunchedEffect(instanceId) {
        viewModel.loadData(instanceId)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "List Category: $instanceId",
            fontSize = 24.sp,
        )
        Spacer( modifier = Modifier.size(0.01 * screenHeight) )
        when (val currentState = state) {
            is ListWidgetState.Loading -> {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is ListWidgetState.Success -> {
                currentState.items.forEach { item ->
                    Text(
                        text = "${item.name} ${item.surname}",
                        fontSize = 18.sp
                    )
                    Spacer( modifier = Modifier.size(0.003 * screenHeight) )
                }
            }
            is ListWidgetState.Error -> {
                Text(
                    text = currentState.message,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListWidgetPreview() {
    ListWidget(instanceId = "preview")
}