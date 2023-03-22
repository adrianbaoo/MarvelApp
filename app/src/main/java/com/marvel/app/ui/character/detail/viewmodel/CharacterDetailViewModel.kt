package com.marvel.app.ui.character.detail.viewmodel

class CharacterDetailViewModel private constructor(
    val name: String,
    val description: String,
    val thumbnail: String
) {

    class Builder(
        var name: String = "",
        var description: String = "",
        var thumbnail: String = ""
    ) {

        fun name(name: String?) = apply { this.name = name ?: "-" }

        fun description(description: String?) = apply { this.description = description ?: "" }

        fun thumbnail(path: String?, extension: String?) = apply {
            if (!path.isNullOrBlank() && !extension.isNullOrBlank()) {
                this.thumbnail = path.plus(".").plus(extension)
            } else {
                this.thumbnail = ""
            }
        }

        fun build() = CharacterDetailViewModel(name, description, thumbnail)
    }
}
