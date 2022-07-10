package br.com.zup.rickandmorty.ui.favoritedcharacterslist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import br.com.zup.rickandmorty.R
import br.com.zup.rickandmorty.data.model.CharacterResult
import br.com.zup.rickandmorty.databinding.ActivityFavoritedCharactersListBinding
import br.com.zup.rickandmorty.ui.characterinfo.view.CharacterInfoActivity
import br.com.zup.rickandmorty.ui.favoritedcharacterslist.viewmodel.FavoritedCharactersListViewModel
import br.com.zup.rickandmorty.ui.viewstate.ViewState
import br.com.zup.rickandmorty.utils.CHAR_KEY
import br.com.zup.rickandmorty.utils.FAV_CHAR_KEY

class FavoritedCharactersListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritedCharactersListBinding

    private val viewModel: FavoritedCharactersListViewModel by lazy {
        ViewModelProvider(this)[FavoritedCharactersListViewModel::class.java]
    }

    private val favoritedCharactersAdapter: FavoritedCharactersAdapter by lazy {
        FavoritedCharactersAdapter(mutableListOf(), this::goToCharacterInfo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritedCharactersListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        initObserver()
        viewModel.getAllFavoritedCharacters()
        showRecyclerView()
    }

    private fun initObserver(){
        viewModel.favoritedCharactersListState.observe(this){
            when(it){
                is ViewState.Success -> {
                    favoritedCharactersAdapter.updateFavoritedCharacterList(it.data as MutableList<CharacterResult>)
                }
                is ViewState.Error -> {
                    Toast.makeText(this, "${it.throwable.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun showRecyclerView(){
        binding.rvFavotiredCharactersList.adapter = favoritedCharactersAdapter
        binding.rvFavotiredCharactersList.layoutManager = GridLayoutManager(this,2)
    }

    private fun goToCharacterInfo(character: CharacterResult){
        val intent = Intent(this, CharacterInfoActivity::class.java)
        intent.putExtra(FAV_CHAR_KEY,character)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}