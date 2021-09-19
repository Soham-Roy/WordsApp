package com.android.wordsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.wordsapp.R
import com.android.wordsapp.databinding.ListItemBinding

class WordsAdapter(context: Context, private val c : Char, private val listener: ItemClickListener):
    RecyclerView.Adapter<WordsAdapter.WordViewHolder>() {

    inner class WordViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val words = context.resources.getStringArray(R.array.words).toList()
    private var filteredWords: List<String> = words.filter { it.startsWith(c, ignoreCase = true) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val bind = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(bind)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        with(holder){
            with( filteredWords[position] ){
                binding.button.text = this
            }
            binding.button.setOnClickListener { listener.onItemClicked(this.adapterPosition) }
        }
    }

    override fun getItemCount(): Int = filteredWords.size

    fun getWord(position: Int) : String = filteredWords[position]

}