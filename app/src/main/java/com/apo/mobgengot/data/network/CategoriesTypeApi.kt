package com.apo.mobgengot.data.network

import com.apo.mobgengot.data.network.book.BookJson
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoriesTypeApi {

    @GET("test{url}/")
    fun getBooks(@Path("url", encoded = true) url: String): Single<List<BookJson>>
}