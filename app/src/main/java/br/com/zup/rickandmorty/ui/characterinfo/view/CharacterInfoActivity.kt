package br.com.zup.rickandmorty.ui.characterinfo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import br.com.zup.rickandmorty.R
import br.com.zup.rickandmorty.data.model.CharacterResult
import br.com.zup.rickandmorty.databinding.ActivityCharacterInfoBinding
import br.com.zup.rickandmorty.ui.characterinfo.viewmodel.CharacterInfoViewModel
import br.com.zup.rickandmorty.utils.CHAR_INFO_ERROR_MSG
import br.com.zup.rickandmorty.utils.CHAR_KEY
import com.squareup.picasso.Picasso

class CharacterInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterInfoBinding
    private lateinit var character: CharacterResult

    private val viewModel: CharacterInfoViewModel by lazy {
        ViewModelProvider(this)[CharacterInfoViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getData()

        binding.iconStar.setOnClickListener {
            character.favorite = !character.favorite
            updateFavoriteCharacter()
            viewModel.updateFavoriteCharacter(character)
        }
    }

    private fun getData(){

        val data = intent.getParcelableExtra<CharacterResult>(CHAR_KEY)

        if(data != null){
            character = data

                character.let {
                    Picasso.get().load(it.image).into(binding.ivCharacterImageDetail)
                    binding.tvCharacterName.text = "Nome: " + it.name
                    binding.tvCharacterGender.text = "Gênero: " + it.gender
                    binding.tvCharacterSpecies.text = "Espécie: " + it.species
                    binding.tvCharacterStatus.text = "Status: " + it.status
                    updateFavoriteCharacter()

                    this.supportActionBar?.title = it.name
                }
        }else{
            Toast.makeText(this, CHAR_INFO_ERROR_MSG, Toast.LENGTH_LONG).show()
        }
    }

    private fun updateFavoriteCharacter(){
        binding.iconStar.setImageDrawable(
            ContextCompat.getDrawable(
                binding.root.context,
                if (character.favorite) R.drawable.ic_baseline_star_rate_40_yellow
                else R.drawable.ic_baseline_star_rate_40
            )
        )

        if(character.favorite){
            Toast.makeText(this, "${character.name} foi favoritado com sucesso!", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "${character.name} foi desfavoritado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}