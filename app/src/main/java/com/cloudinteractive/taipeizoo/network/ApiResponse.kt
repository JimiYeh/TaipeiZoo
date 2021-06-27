package com.cloudinteractive.taipeizoo.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.lang.Exception

sealed class ApiResponse<out T> {
    data class ApiSuccess<out T>(val data: T?) : ApiResponse<T>()
    data class ApiError(val errorInfo: ApiErrorInfo?) : ApiResponse<Nothing>()
    data class ApiException(val exception: Exception) : ApiResponse<Nothing>()
}

suspend inline fun <reified T> callApi(crossinline api: suspend () -> Response<T>): ApiResponse<T> =
    withContext(
        Dispatchers.IO
    ) {

        try {
            val response = api.invoke()

            if (response.isSuccessful) {
                ApiResponse.ApiSuccess(response.body())
            } else {
                val converter: Converter<ResponseBody, ApiErrorInfo> =
                    Client.retrofit.responseBodyConverter(
                        ApiErrorInfo::class.java, arrayOf()
                    )

                response.errorBody()?.let { errorBody ->
                    ApiResponse.ApiError(converter.convert(errorBody))
                } ?: ApiResponse.ApiError(null)
            }
        } catch (e: Exception) {
            ApiResponse.ApiException(e)
        }
    }


data class ApiErrorInfo(
    val statusCode: Int,
    val message: String
)