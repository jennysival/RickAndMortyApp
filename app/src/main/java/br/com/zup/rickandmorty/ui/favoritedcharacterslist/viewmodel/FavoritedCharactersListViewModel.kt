package br.com.zup.rickandmorty.ui.favoritedcharacterslist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.rickandmorty.data.model.CharacterResult
import br.com.zup.rickandmorty.domain.usecase.CharacterUseCase
import br.com.zup.rickandmorty.ui.viewstate.ViewState
import br.com.zup.rickandmorty.utils.FAV_LIST_ERROR_MSG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritedCharactersListViewModel(application: Application): AndroidViewModel(application) {

    private val characterUseCase = CharacterUseCase(application)
    val favoritedCharactersListState = MutableLiveData<ViewState<List<CharacterResult>>>()

    fun getAllFavoritedCharacters(){
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO){
                    characterUseCase.getAllFavoritedCharacters()
                }
                favoritedCharactersListState.value = response
            }catch (e: Exception){
                favoritedCharactersListState.value = ViewState.Error(Exception(FAV_LIST_ERROR_MSG))
            }
        }
    }

}