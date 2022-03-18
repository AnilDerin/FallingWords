package com.anilderin.fallingwords.model

import com.google.gson.annotations.SerializedName

data class WordPairs(
    @SerializedName("text_eng")
    var textEng: String,
    @SerializedName("text_spa")
    var textSpa: String
)