package com.android.wordsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.android.wordsapp.databinding.ActivityMainBinding
import com.android.wordsapp.fragments.FragmentListener
import com.android.wordsapp.fragments.LettersFragment
import com.android.wordsapp.fragments.WordsFragment

class MainActivity : AppCompatActivity() /*, FragmentListener */ {

    private lateinit var bind: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        setSupportActionBar(bind.mainActivityToolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_activity_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

/*
    // Fragment replacement using interface
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
 */

}