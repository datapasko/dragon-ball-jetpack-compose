package com.tapascodev.dragonball.data.mapper

import com.tapascodev.dragonball.data.local.entity.FavoriteCharacterEntity
import com.tapascodev.dragonball.domain.model.CharacterModel

fun CharacterModel.toFavoriteCharacterEntity() : FavoriteCharacterEntity {
    return FavoriteCharacterEntity(
        id = this.id,
        name = this.name,
        ki = this.ki,
        maxKi = this.maxKi,
        affiliation = this.affiliation,
        race = this.race,
        gender = this.gender,
        description = this.description,
        image = this.image
    )
}