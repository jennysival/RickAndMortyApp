package br.com.zup.rickandmorty.data.datasource.remote

import br.com.zup.rickandmorty.data.model.CharacterResponse
import retrofit2.http.GET

interface RickAndMortyAPI {

    @GET("character")
    suspend fun getAllCharactersNetwork(): CharacterResponse
}