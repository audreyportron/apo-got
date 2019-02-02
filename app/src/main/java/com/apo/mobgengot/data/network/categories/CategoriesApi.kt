package com.apo.mobgengot.data.network.categories

import io.reactivex.Single
import retrofit2.http.GET

interface CategoriesApi {
    @GET("index")
    fun getCategories(): Single<List<CategoryJson>>
}