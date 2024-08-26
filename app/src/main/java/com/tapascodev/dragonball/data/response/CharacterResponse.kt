package com.tapascodev.dragonball.data.response

import com.google.gson.annotations.SerializedName
import com.tapascodev.dragonball.domain.model.CharacterModel

data class CharacterResponse (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("ki") val ki: String,
    @SerializedName("maxKi") val maxKi: String,
    @SerializedName("race") val race: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,
    @SerializedName("affiliation") val affiliation: String
) {
    fun toPresentation(): CharacterModel {
        return CharacterModel(
            id, name, ki, maxKi, race, gender, description, image, affiliation
        )
    }
}