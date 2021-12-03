package com.example.lykkehjulet20.model

import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.lykkehjulet20.data.highscores
import com.example.lykkehjulet20.data.listOfWords
import java.text.NumberFormat

class MainViewModel : ViewModel() {

    // Score for the player
    private val _score = MutableLiveData(0)
    val score: LiveData<Int?> = _score

    // Total cost of the order
    private val _lives = MutableLiveData(5)
    val lives: LiveData<Int?> = _lives



    // Initialize data.
    val myDataset = highscores().loadHighscores()

    private var userName: String = "s164539"

    private lateinit var currentWord: String

    init {
        Log.d("GameFragment", "MainViewModel created!")
    }

    fun getWord() {
    currentWord = listOfWords.random()
    }

    fun isWordGuessed(playerWord: String): Boolean {
        if(playerWord.equals(currentWord, true)){

            return true
            }
        return false
    }

    fun restartGame() {
        _score.value = 0

    }


}