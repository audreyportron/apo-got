package com.apo.mobgengot.domain

class CategoriesService(val categoriesRepository: CategoriesRepository) {
    fun getCategoriesByName(forceLoadApi: Boolean) = categoriesRepository.getCategories(forceLoadApi).map {
        it.sortedBy { category -> category.title }
    }
}