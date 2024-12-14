package com.dvxnews.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class DVXNewsStatus {
    @SerialName("success") SUCCESS,
    @SerialName("error") ERROR;
}

internal val DVXNewsStatus.isSuccessful
    get() = when (this) {
        DVXNewsStatus.SUCCESS -> true
        DVXNewsStatus.ERROR -> false
    }
