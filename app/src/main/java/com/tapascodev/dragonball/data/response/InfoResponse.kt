package com.tapascodev.dragonball.data.response

import com.google.gson.annotations.SerializedName

data class InfoResponse (
    @SerializedName("totalItems") val totalItems: Int,
    @SerializedName("itemCount") val itemCount: Int,
    @SerializedName("itemsPerPage") val itemsPerPage: Int,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("currentPage") val currentPage: Int,
)