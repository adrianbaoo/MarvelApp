package com.marvel.data.util

import com.marvel.data.CharacterApiClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val CHARACTER_API_CLIENT: CharacterApiClient =
        getRetrofit().create(CharacterApiClient::class.java)
}
