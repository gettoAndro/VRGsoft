package com.example.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import kotlin.collections.ArrayList



data class CountryInfo(
        var alpha3Code: String,
        var borders: String,
        var nativeName: String
)

