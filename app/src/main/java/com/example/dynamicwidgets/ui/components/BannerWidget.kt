package com.example.dynamicwidgets.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.dynamicwidgets.data.model.BannerConfig
import androidx.compose.foundation.lazy.items

@Composable
fun BannerWidget(banners: List<BannerConfig>) {

    //Screen dimensions for responsive design
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(banners) { banner ->
            Card(
                modifier = Modifier
                    .width(0.8 * screenWidth)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.04 * screenHeight),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = banner.title,
                        fontSize = 24.sp
                    )
                    Spacer( modifier = Modifier.size(0.018 * screenHeight) )
                    Text(
                        text = banner.subtitle,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}