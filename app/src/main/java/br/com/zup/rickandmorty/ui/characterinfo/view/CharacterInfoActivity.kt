package br.com.zup.rickandmorty.ui.characterinfo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.zup.rickandmorty.R
import br.com.zup.rickandmorty.data.model.CharacterResult
import br.com.zup.rickandmorty.databinding.ActivityCharacterInfoBinding
import br.com.zup.rickandmorty.utils.BUNDLE_KEY
import br.com.zup.rickandmorty.utils.CHAR_KEY
import com.squareup.picasso.Picasso

class CharacterInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
    }

    private fun getData(){
        val character = intent.getParcelableExtra<CharacterResult>(CHAR_KEY)

        character?.let {
            Picasso.get().load(it.image).into(binding.ivCharacterImageDetail)

            binding.tvCharacterName.text = it.name
            binding.tvCharacterGender.text = it.gender
            binding.tvCharacterSpecies.text = it.species
            binding.tvCharacterStatus.text = it.status

            this.supportActionBar?.title = it.name
        }
    }
}