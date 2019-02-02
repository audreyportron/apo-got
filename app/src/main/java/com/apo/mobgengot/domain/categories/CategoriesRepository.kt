package com.apo.mobgengot.domain.categories

import io.reactivex.Single

interface CategoriesRepository {
    fun getCategories(forceApiLoad: Boolean): Single<List<Category>>
}