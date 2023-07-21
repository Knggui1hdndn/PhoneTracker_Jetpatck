package com.gps.phonetracker.numberlocator.family.tracklocation.viewModel

import androidx.lifecycle.ViewModel
import com.gps.phonetracker.numberlocator.family.tracklocation.map.Location
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class NumberLocatorViewModel() : ViewModel() {
    private var locationState = MutableStateFlow("")
    val _locationState = locationState.asStateFlow()
    fun setLocation() {
        val locationRequest = Location()
        locationRequest.getFromLocation {
            locationState.value = it
        }
    }
}