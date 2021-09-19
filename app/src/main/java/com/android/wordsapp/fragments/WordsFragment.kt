package com.android.wordsapp.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.wordsapp.R
import com.android.wordsapp.adapters.ItemClickListener
import com.android.wordsapp.adapters.WordsAdapter
import com.android.wordsapp.databinding.FragmentWordsBinding
import java.lang.ClassCastException

class WordsFragment : Fragment(), ItemClickListener {

    private var binding: FragmentWordsBinding? = null

    private val SEARCH_URL = "https://www.google.com/search?q="

    private val bind get() = binding!!
    private lateinit var adapter: WordsAdapter
    private var toast: Toast? = null

    var charReceived: Char? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordsBinding.inflate(inflater, container, false)
        return bind.root
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bind.wordToolbar.navigationIcon = context?.getDrawable(R.drawable.ic_baseline_arrow_back_24)
        bind.wordToolbar.setNavigationOnClickListener {
            listener.onItemClicked(null)
        }
        updateUI(charReceived!!)
    }

    private fun updateUI(c: Char){
        adapter = WordsAdapter(requireContext(), c, this)
        bind.wordRecyclerView.adapter = adapter
        bind.wordRecyclerView.hasFixedSize()
        bind.wordRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onItemClicked(position: Int) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("${SEARCH_URL}${adapter.getWord(position)}+meaning"))
        if ( activity?.packageManager?.resolveActivity(intent, 0) != null ) {
            startActivity(intent)
        }
        else {
            if ( toast != null ) toast!!.cancel()
            toast = Toast.makeText(context, "Item ${adapter.getWord(position)} and no intent", Toast.LENGTH_SHORT)
            toast!!.show()
        }
    }
}