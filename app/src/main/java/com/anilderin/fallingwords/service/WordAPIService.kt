package com.anilderin.fallingwords.service

import com.anilderin.fallingwords.model.WordPairs
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WordAPIService {

    private val BASE_URL = "https://gist.githubusercontent.com/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(WordAPI::class.java)

    fun getWordPairs() : Single<List<WordPairs>> {
        return api.getWords()
    }
}