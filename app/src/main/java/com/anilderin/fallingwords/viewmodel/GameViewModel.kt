package com.anilderin.fallingwords.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anilderin.fallingwords.model.WordPairs
import com.anilderin.fallingwords.service.WordAPIService
import kotlinx.coroutines.launch

class GameViewModel() : ViewModel() {

    private val wordAPIService = WordAPIService()

    val myResponse : MutableLiveData<WordPairs> = MutableLiveData()

    var score = 0
    var lives = 3




    fun getWords() {
        viewModelScope.launch {
            wordAPIService.getWordPairs()
        }
    }

    fun onCorrect() {
        score++
    }

    fun onWrong() {
        lives--
    }


    init {
        Log.i("GameViewModel", "GameViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }

}