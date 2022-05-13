package com.soome.cloudinteractive.repository

import com.soome.cloudinteractive.Api.UserApi
import com.soome.cloudinteractive.di.IoDispatcher
import dagger.Reusable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Reusable
class UserRepository @Inject constructor(
    private val userApi: UserApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getUser(user: String) = withContext(ioDispatcher) {
        userApi.getUser(user)
    }
}