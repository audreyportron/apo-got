package com.apo.mobgengot.domain

import io.reactivex.Single

interface CategoriesRepository {
    fun getCategories(forceApiLoad: Boolean): Single<List<Category>>
}