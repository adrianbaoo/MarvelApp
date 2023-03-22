package com.marvel.app.ui.character.list

import com.marvel.app.ui.character.list.viewmodel.CharacterListViewModel
import com.marvel.data.CharactersRepository
import com.marvel.domain.CharacterModel
import com.marvel.usecases.GetCharactersUseCase
import kotlinx.coroutines.*

class CharacterListPresenter(private var view: View?) {

    private val getCharactersUseCase = GetCharactersUseCase()
    private var characterList: MutableList<CharacterModel> = arrayListOf()
    private var characterViewModelList: MutableList<CharacterListViewModel> = arrayListOf()
    private var offsetRequest: Int = 0
    private var isRequestBeginLaunched = false
    private var areMoreResults = true

    interface View {
        fun renderCharacters(charactersViewModels: List<CharacterListViewModel>)
        fun showProgressBar()
        fun hideProgressBar()
        fun goToCharacterDetail(characterId: Int)
    }

    fun onCreate() {
        isRequestBeginLaunched = true
        getCharacters()
    }

    fun loadMoreCharacters() {
        if (!isRequestBeginLaunched && areMoreResults) {
            view?.showProgressBar()
            isRequestBeginLaunched = true
            getCharacters()
        }
    }

    fun didClickCharacter(characterId: Int) {
        view?.goToCharacterDetail(characterId)
    }

    fun onDestroy() {
        view = null
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getCharacters() = GlobalScope.launch {
        val characters = withContext(Dispatchers.IO) {
            getCharactersUseCase(offsetRequest)
        }

        offsetRequest += characters.count()
        areMoreResults = characters.isNotEmpty()
        isRequestBeginLaunched = false
        characterList.addAll(characters)
        characterViewModelList.addAll(characters.map { mapViewModel(it) })

        withContext(Dispatchers.Main) {
            view?.renderCharacters(characters.map { mapViewModel(it) })
            view?.hideProgressBar()
        }
    }

    private fun mapViewModel(characterModel: CharacterModel): CharacterListViewModel {
        return CharacterListViewModel.Builder()
            .id(characterModel.id)
            .name(characterModel.name)
            .thumbnail(characterModel.thumbnailPath, characterModel.thumbnailExt)
            .build()
    }
}