package com.soome.cloudinteractive.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    homeRepository: HomeRepository,
) : ViewModel() {

    val userList =
        homeRepository
            .getUserListPaging()
            .cachedIn(viewModelScope)
}