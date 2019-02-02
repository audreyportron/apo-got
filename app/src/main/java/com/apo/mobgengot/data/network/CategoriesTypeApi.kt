package com.apo.mobgengot.data.network

import com.apo.mobgengot.data.network.book.BookJson
import io.reactivex.Single
import retrofit2.http.GET

interface CategoriesTypeApi {
    @GET("{url}")
    fun getBooks(url:String): Single<List<BookJson>>
}