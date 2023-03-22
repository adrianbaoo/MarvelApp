package com.marvel.domain

data class CharacterModel(
    val id: Int,
    val name: String?,
    val description: String?,
    val thumbnailPath: String?,
    val thumbnailExt: String?
)
