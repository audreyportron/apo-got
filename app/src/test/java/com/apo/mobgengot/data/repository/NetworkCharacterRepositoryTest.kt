package com.apo.mobgengot.data.repository

import com.apo.mobgengot.data.network.CategoriesTypeApi
import com.apo.mobgengot.data.network.character.CharacterJson
import com.apo.mobgengot.domain.character.Character
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class NetworkCharacterRepositoryTest {
    @Mock
    private lateinit var api: CategoriesTypeApi

    private val repository by lazy {
        NetworkCharacterRepository(api)
    }

    @Test
    fun should_return_characters() {
        //Given
        BDDMockito.given(api.getCharacters("any")).willReturn(Single.just(listOf(characterJson)))
        //When
        val test = repository.getCharacters("any").test()
        //Then
        test.assertNoErrors()
            .assertValueAt(0) {
                it.size == 1
                        && it[0].name == character.name
                        && it[0].aliases[0] == character.aliases[0]
            }
    }

    /** **********************************
     *          DataMock
     *********************************** **/

    val characterJson = CharacterJson(name = "name", aliases = listOf("Aliases"))
    val character = Character(name = "name", aliases = listOf("Aliases"))
}