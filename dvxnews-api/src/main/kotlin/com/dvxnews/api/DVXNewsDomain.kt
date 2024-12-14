package com.dvxnews.api

enum class DVXNewsDomain {
    BABYSCOUT,
    DVXNEWS;

    override fun toString() = when (this) {
        BABYSCOUT -> "babyscout.com"
        DVXNEWS -> "dvxnews.com"
    }
}