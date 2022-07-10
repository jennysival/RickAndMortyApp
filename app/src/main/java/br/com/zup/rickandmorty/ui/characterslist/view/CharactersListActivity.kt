package br.com.zup.rickandmorty.ui.characterslist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import br.com.zup.rickandmorty.data.model.CharacterResult
import br.com.zup.rickandmorty.databinding.ActivityCharactersListBinding
import br.com.zup.rickandmorty.ui.characterinfo.view.CharacterInfoActivity
import br.com.zup.rickandmorty.ui.characterslist.viewmodel.CharacterListViewModel
import br.com.zup.rickandmorty.ui.favoritedcharacterslist.view.FavoritedCharactersListActivity
import br.com.zup.rickandmorty.ui.viewstate.ViewState
import br.com.zup.rickandmorty.utils.CHAR_KEY

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

        binding.favButton.setOnClickListener {
            goToFavoritedCharactersList()
        }
    }

    override fun onResume() {
        super.onResume()
        initObserver()
        viewModel.getAllCharactersNetwork()
        showRecyclerView()
    }

    private fun showRecyclerView(){
        binding.rvCharactersList.adapter = characterAdapter
        binding.rvCharactersList.layoutManager = GridLayoutManager(this, 2)
    }

    private fun initObserver(){
        viewModel.characterListState.observe(this){
            when(it){
                is ViewState.Success -> {
                    characterAdapter.updateCharacterList(it.data as MutableList<CharacterResult>)
                }
                is ViewState.Error -> {
                    Toast.makeText(this, "${it.throwable.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun goToCharacterInfo(character: CharacterResult){
        val intent = Intent(this, CharacterInfoActivity::class.java)
        intent.putExtra(CHAR_KEY,character)
        startActivity(intent)
    }

    private fun goToFavoritedCharactersList(){
            val intent = Intent(this, FavoritedCharactersListActivity::class.java)
            startActivity(intent)
    }

}