package com.marvel.data

import com.marvel.data.model.CharacterApiModel
import com.marvel.data.util.RetrofitBuilder
import com.marvel.domain.CharacterModel
import javax.inject.Singleton

@Singleton
class CharactersRepository {

    private val characterService = CharacterService(RetrofitBuilder.CHARACTER_API_CLIENT)

    suspend fun getCharacters(offset: Int): List<CharacterModel> {
        val response: List<CharacterApiModel> = characterService.getCharacters(offset)
        return response.map { mapToDomain(it) }
    }

    suspend fun getCharacter(characterId: Int): CharacterModel? {
        val response: CharacterApiModel = characterService.getCharacter(characterId) ?: return null
        return mapToDomain(response)
    }

    private fun mapToDomain(apiModel: CharacterApiModel): CharacterModel {
        return CharacterModel(
            apiModel.id!!,
            apiModel.name,
            apiModel.description,
            replacePathProtocol(apiModel.thumbnail?.path),
            apiModel.thumbnail?.extension
        )
    }

    private fun replacePathProtocol(path: String?): String? {
        return path?.replace("http", "https")
    }

}