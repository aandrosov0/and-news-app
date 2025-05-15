package com.dvx.news.ui.core

fun interface ExceptionConverter<T> {
    fun convert(exception: Exception): T
}