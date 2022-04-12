package com.saba.mypokeapp.detail.network.model

import com.google.gson.annotations.SerializedName

data class ApiLocationResponse(
    @SerializedName("id")
    private val id: Int,

    @SerializedName("name")
    private val name: String,

    @SerializedName("region")
    private val region: ApiRegionResponse
)

data class ApiRegionResponse(
    @SerializedName("id")
    private val id: Int,

    @SerializedName("name")
    private val name: String,

    @SerializedName("region")
    private val locations: List<ApiLocationResponse>
)
