package com.apo.mobgengot.tools.di

import com.apo.mobgengot.data.network.categories.CategoryJson
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CategoriesCoroutineApi {

    @GET("test/api1/index")
    fun getCategories(): Deferred<List<CategoryJson>>
}
