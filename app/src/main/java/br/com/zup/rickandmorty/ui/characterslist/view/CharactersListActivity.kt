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

    private val adapter: CharacterAdapter by lazy {
        CharacterAdapter(arrayListOf(), this::goToCharacterInfo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showRecyclerView()
        viewModel.getAllCharactersNetwork()
        initObserver()

    }

    private fun showRecyclerView(){
        binding.rvCharactersList.apply {
            adapter = adapter
            layoutManager = GridLayoutManager(context,2)
        }
    }

    private fun initObserver(){

        viewModel.characterListState.observe(this){
            when(it){
                is ViewState.Success -> {
                    adapter.updateCharacterList(it.data as MutableList<CharacterResult>)
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