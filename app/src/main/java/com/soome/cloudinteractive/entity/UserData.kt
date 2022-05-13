package com.soome.cloudinteractive.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserData(
    @Json(name = "login") val login: String,
    @Json(name = "id") val id: Int,
    @Json(name = "avatar_url") val avatarUrl: String,
    @Json(name = "site_admin") val siteAdmin: Boolean,
    @Json(name = "name") val name: String?,
    @Json(name = "bio") val bio: String?,
    @Json(name = "location") val location: String?,
    @Json(name = "blog") val blog: String?
) {
    val isAdmin = siteAdmin
}
