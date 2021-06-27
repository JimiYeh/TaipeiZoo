package com.cloudinteractive.taipeizoo.network

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class ApiResponseCall<T : Any>(
    private val delegate: Call<T>,
) : Call<ApiResponse<T>> {

    override fun enqueue(callback: Callback<ApiResponse<T>>) {
        return delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@ApiResponseCall,
                            Response.success(ApiResponse.ApiSuccess(body))
                        )
                    } else {
                        // Response is successful but the body is null
                        callback.onResponse(
                            this@ApiResponseCall,
                            Response.success(ApiResponse.ApiError(null, code))
                        )
                    }
                } else {

                    callback.onResponse(
                        this@ApiResponseCall,
                        Response.success(ApiResponse.ApiError(error, code))
                    )

                }
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                callback.onResponse(
                    this@ApiResponseCall,
                    Response.success(ApiResponse.ApiException(throwable))
                )
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = ApiResponseCall(delegate.clone())

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<ApiResponse<T>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}