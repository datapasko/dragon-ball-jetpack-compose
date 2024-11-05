package com.tapascodev.dragonball.data.response

import com.google.gson.annotations.SerializedName
import com.tapascodev.dragonball.domain.model.PlanetModel

data class PlanetResponse (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("isDestroyed") val isDestroyed: Boolean,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,
    @SerializedName("characters")val characters: List<CharacterResponse>?
) {
    fun toDomain(): PlanetModel {
        return PlanetModel(
            id, name, isDestroyed, description, image
        )
    }
}