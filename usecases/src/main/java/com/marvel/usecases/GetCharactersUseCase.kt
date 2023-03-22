package com.marvel.usecases

import com.marvel.data.CharactersRepository
import com.marvel.domain.CharacterModel

class GetCharactersUseCase {

    private val charactersRepository = CharactersRepository()

    suspend operator fun invoke(offset: Int):
            List<CharacterModel> = charactersRepository.getCharacters(offset)

}
