package com.apo.mobgengot.data.network.character

import com.apo.mobgengot.domain.character.Character
import com.google.gson.annotations.SerializedName

data class CharacterJson(
    @SerializedName("aliases") val aliases: List<String>,
    @SerializedName("name") val name: String
)

fun CharacterJson.toCharacter() = Character(aliases = aliases, name = name)