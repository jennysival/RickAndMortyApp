package br.com.zup.rickandmorty.ui.characterinfo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.rickandmorty.data.model.CharacterResult
import br.com.zup.rickandmorty.domain.usecase.CharacterUseCase
import br.com.zup.rickandmorty.ui.viewstate.ViewState
import br.com.zup.rickandmorty.utils.UPDATE_FAV_CHAR_ERROR_MSG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterInfoViewModel(application: Application): AndroidViewModel(application) {
    private val characterUseCase = CharacterUseCase(application)
    private val favoriteCharacterState = MutableLiveData<ViewState<CharacterResult>>()

    fun updateFavoriteCharacter(character: CharacterResult){
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO){
                    characterUseCase.updateFavoriteCharacter(character)
                }
                favoriteCharacterState.value = response
            }catch (e: Exception){
                favoriteCharacterState.value = ViewState.Error(Exception(UPDATE_FAV_CHAR_ERROR_MSG))
            }
        }
    }
}