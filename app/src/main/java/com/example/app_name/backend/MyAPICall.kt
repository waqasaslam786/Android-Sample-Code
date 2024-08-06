package com.example.app_name.backend

import com.example.app_name.models.userDetailModel.UserDetailResponse
import com.example.app_name.models.usersModel.AllUserResponseItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyAPICall {

    @GET("users")
    suspend fun getAllUser(@Query("since") since: Int): List<AllUserResponseItem>

    @GET("users/{ID}")
    suspend fun getUserDetailById(@Path("ID") ID: String): UserDetailResponse

}