package com.dvxnews.api

@Suppress("unused")
enum class DVXNewsLanguage {
    EN,
    DE;

    override fun toString() = name.lowercase()
}