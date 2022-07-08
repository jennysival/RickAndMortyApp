package br.com.zup.rickandmorty.ui.characterslist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import br.com.zup.rickandmorty.data.model.CharacterResult
import br.com.zup.rickandmorty.databinding.ActivityCharactersListBinding
import br.com.zup.rickandmorty.ui.characterslist.viewmodel.CharacterListViewModel
import br.com.zup.rickandmorty.ui.viewstate.ViewState

class CharactersListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharactersListBinding

    private val viewModel: CharacterListViewModel by lazy {
        ViewModelProvider(this)[CharacterListViewModel::class.java]
    }

    private val characterAdapter: CharacterAdapter by lazy {
        CharacterAdapter(mutableListOf(), this::goToCharacterInfo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllCharactersNetwork()
        showRecyclerView()
    }

    private fun showRecyclerView(){
        initObserver()
        binding.rvCharactersList.adapter = characterAdapter
        binding.rvCharactersList.layoutManager = GridLayoutManager(this,2)
    }

    private fun initObserver(){

        viewModel.characterListState.observe(this){
            when(it){
                is ViewState.Success -> {
                    characterAdapter.updateCharacterList(it.data as MutableList<CharacterResult>)
                }
                is ViewState.Error -> {
                    Toast.makeText(this, "${it.errorMsg.message}", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }

        viewModel.loading.observe(this) {
            when (it) {
                is ViewState.Loading -> {
                    binding.loadingBar.isVisible = it.loading == true
                }
                else -> {}
            }
        }
    }

    private fun goToCharacterInfo(character: CharacterResult){

    }

}