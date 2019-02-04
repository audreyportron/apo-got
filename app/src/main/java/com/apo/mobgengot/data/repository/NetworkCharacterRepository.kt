package com.apo.mobgengot.data.repository

import com.apo.mobgengot.data.network.CategoriesTypeApi
import com.apo.mobgengot.data.network.character.toCharacter
import com.apo.mobgengot.domain.character.Character
import com.apo.mobgengot.domain.character.CharacterRepository
import io.reactivex.Single

class NetworkCharacterRepository(val api: CategoriesTypeApi) : CharacterRepository {
    override fun getCharacters(url: String): Single<List<Character>> {
        return api.getCharacters(url).map { charactersJson ->
            charactersJson.map { it.toCharacter() }
        }
    }
}