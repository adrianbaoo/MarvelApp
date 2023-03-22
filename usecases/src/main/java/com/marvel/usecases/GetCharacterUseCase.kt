package com.marvel.usecases

import com.marvel.data.CharactersRepository
import com.marvel.domain.CharacterModel

class GetCharacterUseCase {

    private val charactersRepository = CharactersRepository()

    suspend operator fun invoke(characterId: Int):
            CharacterModel? = charactersRepository.getCharacter(characterId)

}
