package br.com.zup.rickandmorty.ui.characterslist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.rickandmorty.data.model.CharacterResult
import br.com.zup.rickandmorty.domain.model.SingleLiveEvent
import br.com.zup.rickandmorty.domain.usecase.CharacterUseCase
import br.com.zup.rickandmorty.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterListViewModel(application: Application): AndroidViewModel(application) {
    private val characterUseCase = CharacterUseCase(application)
    val characterListState = SingleLiveEvent<ViewState<List<CharacterResult>>>()
    val loading = SingleLiveEvent<ViewState<Boolean>>()

    fun getAllCharactersNetwork(){
        loading.value = ViewState.Loading(true)
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO){
                    characterUseCase.getAllCharactersNetwork()
                }
                characterListState.value = response
            }
            catch (ex: Exception){
                characterListState.value = ViewState.Error(Throwable("Não foi possível carregar a lista de personagens!"))
            }
            finally {
                loading.value = ViewState.Loading(false)
            }
        }
    }

}