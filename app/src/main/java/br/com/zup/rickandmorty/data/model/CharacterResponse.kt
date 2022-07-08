package br.com.zup.rickandmorty.data.model


import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("characterResults")
    val characterResults: List<CharacterResult>
)