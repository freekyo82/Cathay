package com.soome.cloudinteractive.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.soome.cloudinteractive.repository.ListUserDataSource
import dagger.Reusable
import javax.inject.Inject

@Reusable
class HomeRepository @Inject constructor(
    private val listUserDataSource: ListUserDataSource,
) {

    fun getUserListPaging() =
        Pager(PagingConfig(pageSize = REQUEST_COUNT, initialLoadSize = REQUEST_COUNT)) {
            listUserDataSource
        }.flow


    companion object {
        const val REQUEST_COUNT = 20
    }
}