package com.example.app_name.api

import com.example.app_name.backend.MyAPICall
import com.example.app_name.backend.MyCustomApp
import com.example.app_name.util.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SharedWebService(
    private val apiServices: MyAPICall,
    private val app: MyCustomApp
) {


    private val dispatcher = Dispatchers.IO

    suspend fun getAllUserList(sinceId: Int) = withContext(dispatcher) {
        safeApiCall {
            Result.success(apiServices.getAllUser(sinceId))
        }
    }

    suspend fun getUserDetailById(id: String) = withContext(dispatcher) {
        safeApiCall {
            Result.success(apiServices.getUserDetailById(id))
        }
    }
}