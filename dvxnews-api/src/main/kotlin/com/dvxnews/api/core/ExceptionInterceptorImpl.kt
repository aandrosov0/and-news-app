package com.dvxnews.api.core

import com.dvxnews.api.exceptions.DVXConnectionException
import okio.IOException

internal object ExceptionInterceptorImpl : ExceptionInterceptor {
    override fun intercept(exception: Exception) = when (exception) {
        is IOException -> DVXConnectionException("Service is unreachable, please try again later.")
        else -> exception
    }
}