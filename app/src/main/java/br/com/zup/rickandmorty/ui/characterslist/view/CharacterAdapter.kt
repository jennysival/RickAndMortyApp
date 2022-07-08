package br.com.zup.rickandmorty.ui.characterslist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.rickandmorty.data.model.CharacterResult
import br.com.zup.rickandmorty.databinding.CharacterItemBinding
import com.squareup.picasso.Picasso

class CharacterAdapter(
    private var characterList: MutableList<CharacterResult>,
    private val characterClick: (character: CharacterResult) -> Unit
): RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    class ViewHolder(val binding: CharacterItemBinding): RecyclerView.ViewHolder(binding.root){
        fun exibirPersonagem(character: CharacterResult){
            binding.tvCharacterName.text = character.name
            Picasso.get().load(character.image).into(binding.ivCharacterImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characterList[position]
        holder.exibirPersonagem(character)
        holder.binding.cvItemList.setOnClickListener {
            characterClick(character)
        }
        holder.exibirPersonagem(character)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun updateCharacterList(newList: List<CharacterResult>){
        if (characterList.size == 0){
            characterList = newList as MutableList<CharacterResult>
        }else{
            characterList = mutableListOf()
            characterList.addAll(newList)
        }
        notifyDataSetChanged()
    }
}