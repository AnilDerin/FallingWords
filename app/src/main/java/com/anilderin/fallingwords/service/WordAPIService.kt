package com.anilderin.fallingwords.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WordAPIService {

    private val BASE_URL = "https://gist.githubusercontent.com"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WordAPI::class.java)

    suspend fun getWordPairs() {
        api.getWords()
    }
}