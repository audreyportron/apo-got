package com.apo.mobgengot.data.roomdb

import com.apo.mobgengot.domain.Category
import com.apo.mobgengot.domain.toCategoryType

fun Category.toCategoryEntity(): CategoryEntity = CategoryEntity(
    id = id,
    title = title, apiLink = apiLink
)

fun CategoryEntity.toCategory(): Category = Category(
    id = id,
    title = title,
    type = id.toCategoryType(),
    apiLink = apiLink
)


