package com.sevdetneng.watchify.data

sealed class ApiResponse<T>(val data : T? = null,val message : String? = null) {
    class Success<T>(data : T) : ApiResponse<T>(data)
    class Error<T>(message: String? = null) : ApiResponse<T>(message=message)

    class Loading<T>(data : T? = null) : ApiResponse<T>(data)
}