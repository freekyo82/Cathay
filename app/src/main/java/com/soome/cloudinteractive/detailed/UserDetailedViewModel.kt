package com.soome.cloudinteractive.detailed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soome.cloudinteractive.entity.UserData
import com.soome.cloudinteractive.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailedViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _onUserEvent =
        MutableSharedFlow<UserData>(
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    val onUserEvent = _onUserEvent.asSharedFlow()

    fun getUserDetailed(user: String) {
        viewModelScope.launch {
            _onUserEvent.tryEmit(userRepository.getUser(user))
        }
    }
}