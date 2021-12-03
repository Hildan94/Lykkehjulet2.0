package com.example.lykkehjulet20.model

import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.lykkehjulet20.data.listOfWords
import java.text.NumberFormat

class MainViewModel : ViewModel() {

    // Score for the player
    private val _score = MutableLiveData(0)
    val score: LiveData<String> = Transformations.map(_score) {
        NumberFormat.getCurrencyInstance().format(it)
    }
    private lateinit var currentWord: String

    init {
        Log.d("GameFragment", "MainViewModel created!")
    }

    fun getWord() {
    currentWord = listOfWords.random()
    }

    fun restartGame() {
        _score.value = 0

    }

}