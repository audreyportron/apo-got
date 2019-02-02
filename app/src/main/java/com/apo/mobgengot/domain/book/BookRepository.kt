package com.apo.mobgengot.domain.book

import io.reactivex.Single

interface BookRepository{
    fun getBooks(url:String): Single<List<Book>>
}