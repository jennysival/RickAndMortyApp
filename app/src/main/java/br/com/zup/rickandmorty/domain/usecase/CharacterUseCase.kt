package br.com.zup.rickandmorty.domain.usecase

import br.com.zup.rickandmorty.domain.repository.CharacterRepository

class CharacterUseCase {
    private val repository = CharacterRepository()

    suspend fun getAllCharactersNetwork(){

    }
}