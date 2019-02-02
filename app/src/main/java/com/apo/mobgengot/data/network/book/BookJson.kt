package com.apo.mobgengot.data.network.book

import com.apo.mobgengot.domain.book.Book
import com.google.gson.annotations.SerializedName

data class BookJson(
    @SerializedName("authors") val authors: List<String>,
    @SerializedName("country") val country: String,
    @SerializedName("name") val name: String,
    @SerializedName("numberOfPages") val pages: Int
)

fun BookJson.toBook(): Book = Book(
    authors = authors,
    country = country,
    name = name,
    pages = pages
)