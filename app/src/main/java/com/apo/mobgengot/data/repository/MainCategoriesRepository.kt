package com.apo.mobgengot.data.repository

import com.apo.mobgengot.data.network.CategoriesApi
import com.apo.mobgengot.data.network.toCategory
import com.apo.mobgengot.data.roomdb.CategoriesDAO
import com.apo.mobgengot.data.roomdb.toCategory
import com.apo.mobgengot.data.roomdb.toCategoryEntity
import com.apo.mobgengot.domain.CategoriesRepository
import com.apo.mobgengot.domain.Category
import io.reactivex.Single

class MainCategoriesRepository(
    private val categoriesApi: CategoriesApi,
    private val categoriesDao: CategoriesDAO
) : CategoriesRepository {
    override fun getCategories(forceApiLoad: Boolean): Single<List<Category>> {

        val categories =
            if (forceApiLoad) null
            else categoriesDao.getAll().map { categoriesEntity -> categoriesEntity.map { it.toCategory() } }
                .onErrorReturn { null }

        return if (categories != null) categories
        else categoriesApi.getCategories().map { categoriesJson ->
            categoriesJson.map { categoryJson ->
                categoryJson.toCategory()
            }
        }.doOnSuccess {
            val categoryEntities = it.map { category -> category.toCategoryEntity() }
            categoriesDao.insertAll(categoryEntities)
        }.onErrorResumeNext { t ->
            //Try to get datas from db
            categoriesDao.getAll().map {
                it.let { entities ->
                    entities.map { entity ->
                        entity.toCategory()
                    }
                }
            }
        }
    }
}
