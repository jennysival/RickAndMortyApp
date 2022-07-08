package br.com.zup.rickandmorty.ui.characterslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.rickandmorty.data.model.CharacterResponse
import br.com.zup.rickandmorty.data.model.CharacterResult
import br.com.zup.rickandmorty.databinding.CharacterItemBinding

class CharacterAdapter(
    private var characterList: MutableList<CharacterResult>,
    private val clickCharacter: (character: CharacterResult) -> Unit
): RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    class ViewHolder(val binding: CharacterItemBinding): RecyclerView.ViewHolder(binding.root){

        fun showCharacter(character: CharacterResult){
            binding.tvCharacterName.text = character.name
            //TODO Adicionar imagem da API com picasso
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characterList[position]
        holder.showCharacter(character)
        holder.binding.cvItemList.setOnClickListener {
            clickCharacter(character)
        }
    }

    override fun getItemCount(): Int = characterList.size

    fun updateCharacterList(newList: MutableList<CharacterResult>){
        characterList = newList
        notifyDataSetChanged()
    }

}