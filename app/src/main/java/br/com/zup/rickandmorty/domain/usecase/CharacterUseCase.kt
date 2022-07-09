package br.com.zup.rickandmorty.domain.usecase

import android.app.Application
import br.com.zup.rickandmorty.data.model.CharacterResult
import br.com.zup.rickandmorty.domain.repository.CharacterRepository
import br.com.zup.rickandmorty.ui.viewstate.ViewState

class CharacterUseCase(application: Application) {
    private val repository = CharacterRepository()

    suspend fun getAllCharactersNetwork(): ViewState<List<CharacterResult>>{
        return try {
            val characters = repository.getAllCharactersNetwork()
            ViewState.Success(characters.characterResults)
        }catch (e: Exception){
            ViewState.Error(Exception("Não foi possível carregar a lista"))
        }
    }
}