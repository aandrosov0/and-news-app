package com.dvxnews.api

enum class DVXNewsDomain {
    BABYSCOUT,
    DVXNEWS,
    HARMONIE;

    override fun toString() = when (this) {
        BABYSCOUT -> "babyscout.com"
        DVXNEWS -> "dvxnews.app"
        HARMONIE -> "harmonie.de"
    }
}