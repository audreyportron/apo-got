package com.apo.mobgengot.data.network.categories

import com.apo.mobgengot.domain.categories.Category
import com.apo.mobgengot.domain.categories.toCategoryType


fun CategoryJson.toCategory(): Category =
    Category(
        id = id,
        title = title,
        type = id.toCategoryType(),
        apiLink = apiLink
    )


