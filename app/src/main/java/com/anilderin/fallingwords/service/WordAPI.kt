package com.anilderin.fallingwords.service

import com.anilderin.fallingwords.model.WordPairs
import com.anilderin.fallingwords.util.Constants.Companion.API_URL
import io.reactivex.Single
import retrofit2.http.GET

interface WordAPI {

    @GET(API_URL)
    fun getWords(): Single<List<WordPairs>>
}