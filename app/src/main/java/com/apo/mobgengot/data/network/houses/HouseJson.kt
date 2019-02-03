package com.apo.mobgengot.data.network.houses

import com.apo.mobgengot.domain.house.House
import com.google.gson.annotations.SerializedName

data class HouseJson(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("region") val region: String,
    @SerializedName("title") val title: String
)

fun HouseJson.toHouse() = House(
    id = id,
    name = name,
    region = region,
    title = title
)