package com.soome.cloudinteractive.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.soome.cloudinteractive.Api.UserApi
import com.soome.cloudinteractive.entity.UserData
import dagger.Reusable
import javax.inject.Inject

@Reusable
class ListUserDataSource @Inject constructor(
    private val userApi: UserApi
) : PagingSource<Int, UserData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserData> {
        val page = params.key ?: 0
        val pageSize = params.loadSize
        val result = userApi.getUsers(since = page, per_page = pageSize)
        val nextKey = if (result.size < pageSize) null else page + 1
        return try {
            LoadResult.Page(result, prevKey = null, nextKey =  nextKey)
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, UserData>): Int? {
        return null
    }
}