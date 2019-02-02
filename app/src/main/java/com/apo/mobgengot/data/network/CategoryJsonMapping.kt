package com.apo.mobgengot.data.network

import com.apo.mobgengot.domain.Category
import com.apo.mobgengot.domain.toCategoryType


fun CategoryJson.toCategory(): Category = Category(
    id = id,
    title = title,
    type = id.toCategoryType(),
    apiLink = apiLink
)


