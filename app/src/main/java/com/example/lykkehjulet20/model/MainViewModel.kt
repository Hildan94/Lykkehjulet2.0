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

    // Highscore data
    val myDataset = highscores().loadHighscores()
    var savedWord = ""


    //attributes to be used for getting a category and making
    //the chosen word hidden
    private var categoryInt = categories
    private lateinit var categoryList: List<String>
    private lateinit var categoryChosenList: String
    private lateinit var currentWord: String

    //When this interface is intialized it should get a word
    init {
        Log.d("GameFragment", "MainViewModel created!")
        setWord()
    }

    /*
    Get a category from the words list
     */
    private fun getCategory() {
        val category = categoryInt.random()
        _category.value = category

        when(category){
            "Dyr" -> categoryList = Dyr
            "MadOgDrikke" -> categoryList = MadOgDrikke
            "Plante" -> categoryList = Plante
            "Farver" -> categoryList = Farver
        }
        categoryChosenList = category
    }

    /*
    Get's a word from the chosen category and
    makes the attribute currentWord hide
    it's characters via '-'
     */
    private fun setWord() {
        getCategory()
        currentWord = categoryList.random()

        //builds a string
        val builder = StringBuilder()

        //every letter is replaced by '-'
        for(i in currentWord.indices){
            builder.append("-")
        }
        _word.value = builder.toString()
        savedWord = builder.toString()
    }

    /*
    Function used for when the player makes a guess
     */
    fun guessLetter(singleLetter: Char ): Boolean{
        var letterPresent: Boolean = false
        for (i in currentWord.indices) {
            if (currentWord[i].lowercase() == singleLetter.lowercase()){
                savedWord = savedWord.replaceRange(i, i + 1, singleLetter.toString())
                _word.value = savedWord
                letterPresent = true
            }
        }
        if (letterPresent)
        {
            return true
        }
        decreaseLife()
        return false
    }

    /*
    Function to check if the word has been guessed
     */
    fun isWordGuessed(): Boolean {
        for (i in savedWord.indices) {
            if (savedWord[i].lowercase() == "-") {
                return false
            }
        }
        return true
    }

    /*
    decrements the life attribute
     */
    fun decreaseLife() {
        _lives.value= (_lives.value)?.minus(1)
    }

    /*
    should reinitialise all attributes

     */
    fun restartGame() {
        _score.value = 0
        _lives.value = 5
        _word.value = ""
        _category.value =""
        savedWord=""
        setWord()

        TODO("check is there are more attributes that should be reset")
    }



}