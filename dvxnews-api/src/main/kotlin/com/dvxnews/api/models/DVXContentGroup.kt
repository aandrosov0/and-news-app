package com.dvxnews.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class DVXContentGroup {
    @SerialName("captions") CAPTIONS,
    @SerialName("faq") FAQ,
    @SerialName("meta") META,
    @SerialName("promo") PROMO,
    @SerialName("text") TEXT
}