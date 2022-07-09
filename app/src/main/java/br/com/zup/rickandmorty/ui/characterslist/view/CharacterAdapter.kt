package br.com.zup.rickandmorty.ui.characterslist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.rickandmorty.data.model.CharacterResult
import br.com.zup.rickandmorty.databinding.CharacterItemBinding
import com.squareup.picasso.Picasso

class CharacterAdapter(
    private var characterList: MutableList<CharacterResult> = mutableListOf(),
    private val characterClick: (character: CharacterResult) -> Unit
): RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    class ViewHolder(val binding: CharacterItemBinding): RecyclerView.ViewHolder(binding.root){
        fun showCharacter(character: CharacterResult){
            Picasso.get().load(character.image).into(binding.ivCharacterImage)
            binding.tvCharacterName.text = character.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characterList[position]
        holder.showCharacter(character)
        holder.binding.cvItemList.setOnClickListener {
            characterClick(character)
        }
    }

    override fun getItemCount() = characterList.size


    fun updateCharacterList(newList: MutableList<CharacterResult>){
        characterList = newList
        notifyDataSetChanged()
    }
}