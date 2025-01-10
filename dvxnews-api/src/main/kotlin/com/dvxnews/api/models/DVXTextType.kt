package com.dvxnews.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class DVXTextType {
    @SerialName("subheadline") SUBHEADLINE,
    @SerialName("headline") HEADLINE,
    @SerialName("lead_paragraph") LEAD_PARAGRAPH,
    @SerialName("subheading") SUBHEADING,
    @SerialName("paragraph") PARAGRAPH,
}