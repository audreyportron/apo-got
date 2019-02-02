package com.apo.mobgengot.domain

class CategoriesService(val categoriesRepository: CategoriesRepository) {
    fun getCategoriesByName(forceLoadApi: Boolean) = categoriesRepository.getCategories(forceLoadApi).map {
        it.filter{category -> category.type!=CategoryType.UNKNOWN}
            .sortedBy { category -> category.title }
    }
}