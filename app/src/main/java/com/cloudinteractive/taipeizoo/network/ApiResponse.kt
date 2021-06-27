package com.cloudinteractive.taipeizoo.network

import okhttp3.ResponseBody

sealed class ApiResponse<out T> {
    data class ApiSuccess<out T>(val data: T) : ApiResponse<T>()
    data class ApiError(val errorBody: ResponseBody?, val httpCode: Int) : ApiResponse<Nothing>()
    data class ApiException(val exception: Throwable) : ApiResponse<Nothing>()
}
