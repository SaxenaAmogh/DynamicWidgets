package com.example.dynamicwidgets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dynamicwidgets.data.model.ListWidgetState
import com.example.dynamicwidgets.data.repository.Repository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListWidgetViewModel : ViewModel() {

    private val _state = MutableStateFlow<ListWidgetState>(ListWidgetState.Loading)
    val state = _state.asStateFlow()

    // This flag ensures we don't fetch twice if the UI recomposes
    private var isDataLoaded = false

    fun loadData(instanceId: String) {
        if (isDataLoaded) return

        viewModelScope.launch {
            _state.value = ListWidgetState.Loading
            try {
                // The heavy lifting (Business Logic) is now here
                val data = Repository.getListData(instanceId)
                _state.value = ListWidgetState.Success(data)
                isDataLoaded = true
            } catch (_: Exception) {
                _state.value = ListWidgetState.Error("Failed to load data")
            }
        }
    }
}