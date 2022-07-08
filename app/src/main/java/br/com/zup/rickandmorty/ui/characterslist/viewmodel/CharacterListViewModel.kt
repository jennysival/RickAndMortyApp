package br.com.zup.rickandmorty.ui.characterslist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.rickandmorty.data.model.CharacterResult
import br.com.zup.rickandmorty.domain.SingleLiveEvent
import br.com.zup.rickandmorty.domain.usecase.CharacterUseCase
import br.com.zup.rickandmorty.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterListViewModel(application: Application): AndroidViewModel(application) {
    val characterUseCase = CharacterUseCase(application)
    private val _characterListState = SingleLiveEvent<ViewState<List<CharacterResult>>>()
    val characterListState = _characterListState
    val loading = SingleLiveEvent<ViewState<Boolean>>()

    fun getAllCharactersNetwork(){
        loading.value = ViewState.Loading(true)
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO){
                    characterUseCase.getAllCharactersNetwork()
                }
                _characterListState.value = response
            }
            catch (ex: Exception){
                _characterListState.value = ViewState.Error(Throwable("Não foi possível carregar a lista de personagens!"))
            }
            finally {
                loading.value = ViewState.Loading(false)
            }
        }
    }

}