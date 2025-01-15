package com.dvxnews.api.core

internal fun interface ExceptionInterceptor {
    fun intercept(exception: Exception): Exception
}