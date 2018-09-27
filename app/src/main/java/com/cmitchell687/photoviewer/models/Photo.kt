package com.cmitchell687.photoviewer.models

import com.squareup.moshi.Json

data class Photo (
        @Json(name = "id") val id: Long,
        @Json(name = "author") val author: String
)
