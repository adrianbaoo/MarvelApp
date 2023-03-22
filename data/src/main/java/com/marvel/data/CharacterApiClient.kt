package com.marvel.data

import com.marvel.data.model.CharacterDataWrapperApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiClient {

    @GET("characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Response<CharacterDataWrapperApiModel>

    @GET("characters/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Response<CharacterDataWrapperApiModel>

}