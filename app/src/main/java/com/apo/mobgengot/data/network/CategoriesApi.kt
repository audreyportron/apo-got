package com.apo.mobgengot.data.network

import io.reactivex.Single
import retrofit2.http.GET

interface CategoriesApi {
    @GET("index")
    fun getCategories(): Single<List<CategoryJson>>
}