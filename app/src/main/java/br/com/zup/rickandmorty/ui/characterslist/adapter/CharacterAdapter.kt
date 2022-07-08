package br.com.zup.rickandmorty.ui.characterslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.rickandmorty.data.CharacterResponse
import br.com.zup.rickandmorty.databinding.CharacterItemBinding
import com.squareup.picasso.Picasso

class CharacterAdapter(
    private var characterList: MutableList<CharacterResponse>,
    private val clickCharacter: (character: CharacterResponse) -> Unit
): RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    class ViewHolder(val binding: CharacterItemBinding): RecyclerView.ViewHolder(binding.root){

        fun showCharacter(character: CharacterResponse){
            binding.tvCharacterName.text = character.nome
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

    fun updateCharacterList(newList: MutableList<CharacterResponse>){
        characterList = newList
        notifyDataSetChanged()
    }

}