package com.apo.mobgengot.ui.characters

import com.apo.mobgengot.BR
import com.apo.mobgengot.R
import com.apo.mobgengot.domain.character.Character
import com.apo.mobgengot.ui.binding.AutobindedViewModel

class CharacterItemViewModel(
    private val character: Character,
    override val layout: Int = R.layout.character_item,
    override val variable: Int = BR.model
) : AutobindedViewModel {


    val characterTextArgs = if (character.aliases.firstOrNull().isNullOrBlank()) arrayOf(character.name)
    else arrayOf(character.name, character.aliases.first())

    val characterTextId = if (character.aliases.firstOrNull().isNullOrBlank()) R.string.character_empty_alias
    else R.string.character_with_alias
}
