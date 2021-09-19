package com.android.wordsapp.fragments

interface FragmentListener {
    /*
    * Pass null when up button is clicked
     */
    fun onItemClicked(c: Char?)
}