package br.com.zup.rickandmorty.ui.characterinfo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import br.com.zup.rickandmorty.data.model.CharacterResult
import br.com.zup.rickandmorty.databinding.ActivityCharacterInfoBinding
import br.com.zup.rickandmorty.utils.CHAR_KEY
import com.squareup.picasso.Picasso

class CharacterInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getData()
    }

    private fun getData(){
        val character = intent.getParcelableExtra<CharacterResult>(CHAR_KEY)

        character?.let {
            Picasso.get().load(it.image).into(binding.ivCharacterImageDetail)
            binding.tvCharacterName.text = "Nome: " + it.name
            binding.tvCharacterGender.text = "Gênero: " + it.gender
            binding.tvCharacterSpecies.text = "Espécie: " + it.species
            binding.tvCharacterStatus.text = "Status: " + it.status

            this.supportActionBar?.title = it.name
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