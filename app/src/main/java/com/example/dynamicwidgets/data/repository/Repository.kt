package com.example.dynamicwidgets.data.repository

import com.example.dynamicwidgets.data.model.BannerConfig
import com.example.dynamicwidgets.data.model.ListWidgetConfig
import com.example.dynamicwidgets.data.model.WidgetResponse
import kotlinx.coroutines.delay

object Repository {
    fun getDashboardMetadata(): List<WidgetResponse> {
        return listOf(
            WidgetResponse(type = "banner", instanceId = "pokemon"),
            WidgetResponse(type = "banner", instanceId = "cars"),
            WidgetResponse(type = "list", instanceId = "actors"),
            WidgetResponse(type = "banner", instanceId = "bikes"),
            WidgetResponse(type = "list", instanceId = "shows")
        )
    }

    fun getBannerData(instanceId: String): List<BannerConfig> {
        return when (instanceId) {
            "pokemon" -> listOf(
                BannerConfig("Pikachu", "Electric"),
                BannerConfig("Charmander", "Fire")
            )
            "cars" -> listOf(
                BannerConfig("Ferrari", "Fast"),
                BannerConfig("Lamborghini", "Faster"),
                BannerConfig("Bugatti", "Fastest")
            )
            "bikes" -> listOf(
                BannerConfig("Harley", "Classic"),
            )
            else -> emptyList()
        }
    }

    suspend fun getListData(instanceId: String): List<ListWidgetConfig> {
        delay(1000) //Delay to show the Loading
        return when (instanceId) {
            "actors" -> listOf(
                ListWidgetConfig("Tom", "Cruise"),
                ListWidgetConfig("Sadie", "Sink"),
                ListWidgetConfig("Chris", "Evans"),
                ListWidgetConfig("Bryan", "Cranston"),
                ListWidgetConfig("Robert", "Downey Jr."),
                ListWidgetConfig("Bradd", "Pitt")
            )
            "shows" -> listOf(
                ListWidgetConfig("Stranger", "Things"),
                ListWidgetConfig("Breaking", "Bad"),
                ListWidgetConfig("The", "Office"),
                ListWidgetConfig("Game", "of Thrones")
            )
            else -> emptyList()
        }
    }
}