package com.marvel.app.ui.character.list.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.marvel.app.R
import com.marvel.app.ui.character.list.viewmodel.CharacterListViewModel
import com.marvel.app.util.inflate

class CharacterListAdapter : RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    private var items: MutableList<CharacterListViewModel> = arrayListOf()
    private lateinit var listener: CharacterItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.character_list_card))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val characterViewModel = items[position]
        val context = holder.itemView.context
        Glide.with(context).load(characterViewModel.thumbnail).into(holder.characterImageIV)
        holder.characterNameTV.text = characterViewModel.name

        holder.itemView.setOnClickListener {
            listener.didClickItem(characterViewModel.id)
        }
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(items: List<CharacterListViewModel>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun setListener(listener: CharacterItemClickListener) {
        this.listener = listener
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val characterImageIV: ImageView = itemView.findViewById(R.id.character_list_image_iv)
        val characterNameTV: TextView = itemView.findViewById(R.id.character_list_name_tv)
    }

    interface CharacterItemClickListener {
        fun didClickItem(characterId: Int)
    }
}
