package com.anilderin.fallingwords.model

import com.google.gson.annotations.SerializedName

data class WordPairs(
    @SerializedName("text_eng")
    val textEng: String,
    @SerializedName("text_spa")
    val textSpa: String
)