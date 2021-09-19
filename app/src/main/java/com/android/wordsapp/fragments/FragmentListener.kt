package com.android.wordsapp.fragments

interface FragmentListener {
    /*
    * Pass (c = null) when up button is clicked
     */
    fun onItemClicked(c: Char?)
}