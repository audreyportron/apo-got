package com.apo.mobgengot.data.repository

import com.apo.mobgengot.data.network.CategoriesTypeApi
import com.apo.mobgengot.data.network.book.toBook
import com.apo.mobgengot.domain.book.Book
import com.apo.mobgengot.domain.book.BookRepository
import io.reactivex.Single

class NetworkBookRepository(val api: CategoriesTypeApi) : BookRepository {
    override fun getBooks(url: String): Single<List<Book>> {
        return api.getBooks(url).map { books ->
            books.map { it.toBook() }
        }
    }
}