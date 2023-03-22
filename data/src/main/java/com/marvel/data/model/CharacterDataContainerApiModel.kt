package com.marvel.data.model

import com.google.gson.annotations.SerializedName

class CharacterDataContainerApiModel(
    @SerializedName("results")
    val results: List<CharacterApiModel>?
)
