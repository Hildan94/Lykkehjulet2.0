package com.example.lykkehjulet20.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lykkehjulet20.data.*

class MainViewModel : ViewModel() {


    // Initialize data.
    // Score for the player
    private val _score = MutableLiveData(0)
    val score: LiveData<Int?> = _score

    // The life counter
    private val _lives = MutableLiveData(5)
    val lives: LiveData<Int?> = _lives

    // The chosen word
    private val _word = MutableLiveData<String>("")
    val word: LiveData<String?> = _word

    // The chosen category
    private val _category = MutableLiveData<String>("")
    val category: LiveData<String?> = _category

    val myDataset = highscores().loadHighscores()
    var savedWord = ""


    private lateinit var categoryList: List<String>
    private lateinit var categoryChosenList: String
    private lateinit var currentWord: String

    init {
        Log.d("GameFragment", "MainViewModel created!")
        setWord()
    }

    private fun getCategory() {
    val category = categories.random()
        _category.value = category

        when(category){
            "Dyr" -> categoryList = Dyr
            "MadOgDrikke" -> categoryList = MadOgDrikke
            "Blomster" -> categoryList = Blomster
            "Farver" -> categoryList = Farver
        }
        categoryChosenList = category
    }

    private fun setWord() {
        getCategory()
        currentWord = categoryList.random()

        val builder = StringBuilder()
        for(i in currentWord.indices){
            builder.append("-")
        }
        _word.value = builder.toString()
        savedWord = builder.toString()
    }


    fun guessLetter(singleLetter: Char ): Boolean{
        for (i in savedWord.indices) {
            if (savedWord[i].lowercase() == singleLetter.lowercase()){
                savedWord = savedWord.replaceRange(i, i+1, singleLetter.toString())
                return true
            }
        }
        decreaseLife()
        return false
    }

    fun isWordGuessed(): Boolean {
        for (i in savedWord.indices) {
            if (savedWord[i].lowercase() == "-") {
                return false
            }
        }
        return true
    }

    fun decreaseLife() {
        _lives.value= (_lives.value)?.minus(1)
    }

    fun restartGame() {
        _score.value = 0

    }



}