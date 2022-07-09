package br.com.zup.rickandmorty.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.zup.rickandmorty.data.model.CharacterResult

@Database(entities = [CharacterResult::class], version = 1)
abstract class CharacterDatabase: RoomDatabase() {
}