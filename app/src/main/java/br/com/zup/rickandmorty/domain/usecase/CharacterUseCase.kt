package br.com.zup.rickandmorty.domain.usecase

import android.app.Application
import br.com.zup.rickandmorty.data.datasource.local.CharacterDatabase
import br.com.zup.rickandmorty.data.model.CharacterResult
import br.com.zup.rickandmorty.domain.repository.CharacterRepository
import br.com.zup.rickandmorty.ui.viewstate.ViewState

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

    suspend fun getAllCharactersDao(): ViewState<List<CharacterResult>>{
        return try {
            val characters = repository.getAllCharactersDao()
            ViewState.Success(characters)
        }catch (e: Exception){
            ViewState.Error(Exception("Não foi possível carregar os personagens offline"))
        }
    }
}