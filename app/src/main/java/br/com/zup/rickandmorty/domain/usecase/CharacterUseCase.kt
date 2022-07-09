package br.com.zup.rickandmorty.domain.usecase

import android.app.Application
import br.com.zup.rickandmorty.data.datasource.local.CharacterDatabase
import br.com.zup.rickandmorty.data.model.CharacterResult
import br.com.zup.rickandmorty.domain.repository.CharacterRepository
import br.com.zup.rickandmorty.ui.viewstate.ViewState
import br.com.zup.rickandmorty.utils.FAV_CHAR_UPDATE_ERROR_MSG
import br.com.zup.rickandmorty.utils.FAV_LIST_ERROR_MSG
import br.com.zup.rickandmorty.utils.OFFLINE_LIST_ERROR_MSG

class CharacterUseCase(application: Application) {
    private val characterDao = CharacterDatabase.getCharacterDatabase(application).characterDao()
    private val repository = CharacterRepository(characterDao)

    suspend fun getAllCharactersNetwork(): ViewState<List<CharacterResult>>{
        return try {
            val characters = repository.getAllCharactersNetwork()
            repository.insertAllCharactersDao(characters.characterResults)
            ViewState.Success(characters.characterResults)
        }catch (e: Exception){
            getAllCharactersDao()
        }
    }

    private suspend fun getAllCharactersDao(): ViewState<List<CharacterResult>>{
        return try {
            val characters = repository.getAllCharactersDao()
            ViewState.Success(characters)
        }catch (e: Exception){
            ViewState.Error(Exception(OFFLINE_LIST_ERROR_MSG))
        }
    }

    suspend fun getAllFavoritedCharacters(): ViewState<List<CharacterResult>>{
        return try {
            val characters = repository.getAllFavoritedCharacters()
            ViewState.Success(characters)
        }catch (e: Exception){
            ViewState.Error(Exception(FAV_LIST_ERROR_MSG))
        }
    }

    suspend fun updateFavoriteCharacter(character: CharacterResult): ViewState<CharacterResult>{
        return try {
            repository.updateFavoriteCharacter(character)
            ViewState.Success(character)
        }catch (e: Exception){
            ViewState.Error(Exception(FAV_CHAR_UPDATE_ERROR_MSG))
        }
    }
}