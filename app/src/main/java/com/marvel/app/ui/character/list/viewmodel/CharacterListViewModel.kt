package com.marvel.app.ui.character.list.viewmodel

class CharacterListViewModel private constructor(
    val id: Int,
    val name: String,
    val thumbnail: String
) {

    class Builder(
        var id: Int = 0,
        var name: String = "",
        var thumbnail: String = ""
    ) {

        fun id(id: Int) = apply { this.id = id }

        fun name(name: String?) = apply { this.name = name ?: "-" }

        fun thumbnail(path: String?, extension: String?) = apply {
            if (!path.isNullOrBlank() && !extension.isNullOrBlank()) {
                this.thumbnail = path.plus(".").plus(extension)
            } else {
                this.thumbnail = ""
            }
        }

        fun build() = CharacterListViewModel(id, name, thumbnail)
    }
}
