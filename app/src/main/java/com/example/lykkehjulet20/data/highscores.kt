package com.example.lykkehjulet20.data

import com.example.lykkehjulet20.R
import com.example.lykkehjulet20.model.Highscores

class highscores {
    fun  loadHighscores(): List<Highscores>{
        return listOf<Highscores>(
            Highscores(R.string.highcores1),
            Highscores(R.string.highcores2),
            Highscores(R.string.highcores3),
            Highscores(R.string.highcores4),
            Highscores(R.string.highcores5),
            Highscores(R.string.highcores6),
            Highscores(R.string.highcores7),
            Highscores(R.string.highcores8),
            Highscores(R.string.highcores9),
            Highscores(R.string.highcores10)
        )
    }
}