package com.tapascodev.dragonball.data.response

import com.google.gson.annotations.SerializedName

class ResponsePlanetWrapper (
    @SerializedName("items") val items: List<PlanetResponse>,
)