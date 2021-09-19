package com.android.wordsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.wordsapp.databinding.ListItemBinding

class LettersAdapter(private val listener: ItemClickListener) :
    RecyclerView.Adapter<LettersAdapter.LetterViewHolder>() {

    private val list = ('A').rangeTo('Z').toList()

    class LetterViewHolder(val bind: ListItemBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val bind = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LetterViewHolder(bind)
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        with(holder){
            with(list[position]){
                bind.button.text = this.toString()
            }
            bind.button.setOnClickListener{
                listener.onItemClicked(this.adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int = list.size

}