package com.example.dynamicwidgets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dynamicwidgets.data.model.WidgetResponse
import com.example.dynamicwidgets.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _widgets = MutableStateFlow<List<WidgetResponse>>(emptyList())
    val widgets = _widgets.asStateFlow()

    init {
        loadDashboard()
    }

    private fun loadDashboard() {
        viewModelScope.launch {
            // Fetch the list of widgets (Banner, List, Banner...)
            _widgets.value = Repository.getDashboardMetadata()
        }
    }
}