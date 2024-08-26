package com.tapascodev.dragonball.data.response

import com.google.gson.annotations.SerializedName

data class ResponseWrapper (
    @SerializedName("meta") val meta: InfoResponse,
    @SerializedName("items") val items: List<CharacterResponse>,
)