package com.anilderin.fallingwords.service

import com.anilderin.fallingwords.model.WordPairs
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface WordAPI {

    @GET("/DroidCoder/7ac6cdb4bf5e032f4c737aaafe659b33/raw/baa9fe0d586082d85db71f346e2b039c580c5804/words.json")
    fun getWords(): Single<List<WordPairs>>
}