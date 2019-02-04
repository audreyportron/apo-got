package com.apo.mobgengot.domain.character

import io.reactivex.Single

interface CharacterRepository {
    fun getCharacters(url: String): Single<List<Character>>
}