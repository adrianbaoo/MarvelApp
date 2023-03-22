package com.marvel.data.model

import com.google.gson.annotations.SerializedName

class CharacterImageApiModel(
    @SerializedName("path")
    val path: String?,

    @SerializedName("extension")
    val extension: String?
)
