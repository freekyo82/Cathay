package com.soome.cloudinteractive.Api

import com.soome.cloudinteractive.entity.UserData
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @Headers("Accept: application/vnd.github.v3.full+json")
    @GET("/users/{username}")
    suspend fun getUser(@Path("username") username: String): UserData

    @Headers("Accept: application/vnd.github.v3.full+json")
    @GET("/users")
    suspend fun getUsers(@Query("since") since: Int, @Query("per_page") per_page: Int): List<UserData>
}
