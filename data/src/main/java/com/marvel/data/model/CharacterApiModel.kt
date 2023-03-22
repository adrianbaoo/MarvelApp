package com.marvel.data.model

import com.google.gson.annotations.SerializedName

class CharacterApiModel(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("thumbnail")
    val thumbnail: CharacterImageApiModel?
)
