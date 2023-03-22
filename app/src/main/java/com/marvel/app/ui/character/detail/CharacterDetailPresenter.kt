package com.marvel.app.ui.character.detail

import com.marvel.app.ui.character.detail.viewmodel.CharacterDetailViewModel
import com.marvel.domain.CharacterModel
import com.marvel.usecases.GetCharacterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailPresenter(private var view: View?) {

    private val getCharacterUseCase = GetCharacterUseCase()

    interface View {
        fun renderCharacter(characterViewModel: CharacterDetailViewModel)
        fun showErrorWhenGettingCharacter()
        fun hideProgressBar()
    }

    fun onCreate(characterId: Int) = GlobalScope.launch {
        val character = withContext(Dispatchers.IO) {
            getCharacterUseCase(characterId)
        }

        withContext(Dispatchers.Main) {
            if (character == null) {
                view?.showErrorWhenGettingCharacter()
            } else {
                view?.renderCharacter(mapViewModel(character))
            }
            view?.hideProgressBar()
        }
    }

    fun onDestroy() {
        view = null
    }

    private fun mapViewModel(characterModel: CharacterModel): CharacterDetailViewModel {
        return CharacterDetailViewModel.Builder()
            .name(characterModel.name)
            .description(characterModel.description)
            .thumbnail(characterModel.thumbnailPath, characterModel.thumbnailExt)
            .build()
    }
}