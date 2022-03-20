package com.anilderin.fallingwords.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anilderin.fallingwords.model.WordPairs
import com.anilderin.fallingwords.service.WordAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class GameViewModel : ViewModel() {

    private val wordAPIService = WordAPIService()
    private val disposable = CompositeDisposable()

    private val _wordPairLiveData = MutableLiveData<WordPairs>()
    val wordPairLiveData: LiveData<WordPairs> = _wordPairLiveData


    var score = 0
    var lives = 3
    var wordIndex = (0..277).random()

    init {
        Log.i("GameViewModel", "GameViewModel created!")
    }

    fun getWords() {
        disposable.add(
            wordAPIService.getWordPairs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<WordPairs>>() {
                    override fun onSuccess(response: List<WordPairs>) {
                        _wordPairLiveData.value = response[wordIndex]
                        wordIndex++
                        println(wordIndex)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Log.d("GameViewModel", "Data fetch unsuccessful")
                    }
                })
        )
    }


    fun onCorrect() {
        score++
    }

    fun onWrong() {
        when {
            lives > 0 -> lives--
        }
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }


}