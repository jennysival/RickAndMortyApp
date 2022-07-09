package br.com.zup.rickandmorty.data.datasource.local.dao

import androidx.room.*
import br.com.zup.rickandmorty.data.model.CharacterResult

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_table")
    fun getAllCharacters(): List<CharacterResult>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllCharacters(characterList: List<CharacterResult>)

    @Query("SELECT * FROM character_table WHERE favorite = 1")
    fun getAllFavoritedCharacters(): List<CharacterResult>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateFavoriteCharacter(character: CharacterResult)

}