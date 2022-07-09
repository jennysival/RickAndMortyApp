package br.com.zup.rickandmorty.domain.repository

import br.com.zup.rickandmorty.data.datasource.local.dao.CharacterDao
import br.com.zup.rickandmorty.data.datasource.remote.RetrofitService
import br.com.zup.rickandmorty.data.model.CharacterResponse
import br.com.zup.rickandmorty.data.model.CharacterResult

class CharacterRepository(private val characterDao: CharacterDao) {

    suspend fun getAllCharactersNetwork(): CharacterResponse{
        return RetrofitService.apiService.getAllCharactersNetwork()
    }

    suspend fun getAllCharactersDao(): List<CharacterResult> = characterDao.getAllCharacters()

    suspend fun insertAllCharactersDao(characterList: List<CharacterResult>){
        characterDao.insertAllCharacters(characterList)
    }

    suspend fun getAllFavoritedCharacters(): List<CharacterResult> = characterDao.getAllFavoritedCharacters()

    suspend fun updateFavoriteCharacter(character: CharacterResult){
        characterDao.updateFavoriteCharacter(character)
    }
}