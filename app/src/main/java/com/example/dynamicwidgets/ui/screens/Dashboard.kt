package com.example.dynamicwidgets.ui.screens

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dynamicwidgets.data.repository.Repository
import com.example.dynamicwidgets.ui.components.BannerWidget
import com.example.dynamicwidgets.ui.components.ListWidget
import com.example.dynamicwidgets.viewmodel.DashboardViewModel

@Composable
fun Dashboard() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val view = LocalView.current
    val window = (view.context as? Activity)?.window
    val windowInsetsController = window?.let { WindowCompat.getInsetsController(it, view) }
    if (windowInsetsController != null) {
        windowInsetsController.isAppearanceLightStatusBars = true
    }

    val viewModel: DashboardViewModel = viewModel()
    val widgets by viewModel.widgets.collectAsState()

    Scaffold(
        content = {innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        vertical = innerPadding.calculateTopPadding(),
                        horizontal = 0.04 * screenWidth
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Widgets Dashboard",
                    fontSize = 32.sp
                )
                Spacer( modifier = Modifier.size(0.025 * screenHeight) )
                LazyColumn {
                    items(widgets) { widget ->
                        when (widget.type) {
                            "banner" -> {
                                val banners = Repository.getBannerData(widget.instanceId)
                                BannerWidget(banners = banners)
                                Spacer( modifier = Modifier.size(0.025 * screenHeight) )
                            }
                            "list" -> {
                                ListWidget(instanceId = widget.instanceId)
                                Spacer( modifier = Modifier.size(0.025 * screenHeight) )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    Dashboard()
}