package br.com.zup.rickandmorty.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.zup.rickandmorty.data.model.CharacterResult

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_table")
    fun getAllCharacters(): List<CharacterResult>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllCharacters(characterList: List<CharacterResult>)
}