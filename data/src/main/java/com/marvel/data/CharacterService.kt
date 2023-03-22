package com.marvel.data

import com.marvel.data.model.CharacterApiModel
import com.marvel.data.model.CharacterDataWrapperApiModel
import com.marvel.data.util.Constant
import retrofit2.Response

class CharacterService(private val api: CharacterApiClient) {

    suspend fun getCharacters(offset: Int): List<CharacterApiModel> {
        val response = api.getCharacters(
            Constant.MAX_ITEMS_PER_PAGE,
            offset,
            Constant.MARVEL_API_TS,
            Constant.MARVEL_API_KEY,
            Constant.MARVEL_API_HASH
        )
        return getResults(response)
    }

    suspend fun getCharacter(id: Int): CharacterApiModel? {
        val response = api.getCharacter(
            id,
            Constant.MARVEL_API_TS,
            Constant.MARVEL_API_KEY,
            Constant.MARVEL_API_HASH
        )
        return getResult(response)
    }

    private fun getResults(response: Response<CharacterDataWrapperApiModel>): List<CharacterApiModel> {
        return response.body()?.data?.results ?: emptyList()
    }

    private fun getResult(response: Response<CharacterDataWrapperApiModel>): CharacterApiModel? {
        return response.body()?.data?.results?.get(0)
    }

}