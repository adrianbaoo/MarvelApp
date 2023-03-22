package com.marvel.data.model

import com.google.gson.annotations.SerializedName

class CharacterDataWrapperApiModel(
    @SerializedName("data")
    val data: CharacterDataContainerApiModel?
)
