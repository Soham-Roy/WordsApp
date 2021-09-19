package com.android.wordsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.android.wordsapp.fragments.FragmentListener
import com.android.wordsapp.fragments.LettersFragment
import com.android.wordsapp.fragments.WordsFragment

class MainActivity : AppCompatActivity(), FragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onItemClicked(c: Char?) {
        if ( c == null ){
            supportFragmentManager.popBackStack()
            return
        }

        val fragment = WordsFragment()
        fragment.charReceived = c
        supportFragmentManager.commit {
            replace(R.id.main_activity_fragment, fragment)
            setReorderingAllowed(true)
            addToBackStack("name") // name can be null
        }
    }
}