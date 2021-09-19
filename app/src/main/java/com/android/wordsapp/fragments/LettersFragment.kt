package com.android.wordsapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.wordsapp.R
import com.android.wordsapp.adapters.ItemClickListener
import com.android.wordsapp.adapters.LettersAdapter
import com.android.wordsapp.databinding.FragmentLettersBinding
import java.lang.ClassCastException

class LettersFragment : Fragment(), ItemClickListener {

    private var binding: FragmentLettersBinding? = null

    private val bind get() = binding!!
    private var isLinearLayout: Boolean = true
    private val adapter = LettersAdapter(this)

    private var toast: Toast? = null

    private lateinit var listener: FragmentListener

    // Function to throw error if the host activity is not implementing the FragmentListener
    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as FragmentListener
        } catch (e: ClassCastException){
            throw Exception("${context.toString()} must implement FragmentListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLettersBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bind.toolbar.inflateMenu(R.menu.menu_item)
        updateUI()
        bind.toolbar.setOnMenuItemClickListener {
            when (it.itemId){
                R.id.action_layout_button -> {
                    isLinearLayout = !isLinearLayout
                    updateUI()
                    setIcon(it)
                    true
                }
                else -> false
            }
        }
    }

    private fun setIcon(item: MenuItem){
        if ( isLinearLayout ) {
            item.icon =
                context?.let { ContextCompat.getDrawable(it, R.drawable.ic_grid_layout) }
        }
        else {
            item.icon =
                context?.let { ContextCompat.getDrawable(it, R.drawable.ic_linear_layout) }
        }
    }

    private fun updateUI(){
        bind.recyclerView.adapter = adapter
        bind.recyclerView.hasFixedSize()
        if ( isLinearLayout ){
            bind.recyclerView.layoutManager = LinearLayoutManager(context)
        }
        else {
            bind.recyclerView.layoutManager = GridLayoutManager(context, 4)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onItemClicked(position: Int) {
        if ( toast != null ) toast!!.cancel()
        val thisChar: Char = 'A' + position
        toast = Toast.makeText(context, "Item $thisChar clicked", Toast.LENGTH_SHORT)
        toast!!.show()

        listener.onItemClicked(thisChar)
    }
}