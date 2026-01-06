package com.example.dynamicwidgets.data.model

data class WidgetResponse(
    val type: String,       // "banner" or "list"
    val instanceId: String  // e.g., "pokemon", "movies"
)

//Data for the Banner Widget
data class BannerConfig(
    val title: String,
    val subtitle: String
)

//Data for the List Widget
data class ListWidgetConfig(
    val name: String,
    val surname: String
)

//To handle Loading/Success/Error
sealed class ListWidgetState {
    object Loading : ListWidgetState()
    data class Success(val items: List<ListWidgetConfig>) : ListWidgetState()
    data class Error(val message: String) : ListWidgetState()
}