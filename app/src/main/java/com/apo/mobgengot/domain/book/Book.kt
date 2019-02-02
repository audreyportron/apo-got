package com.apo.mobgengot.domain.book

data class Book(
    val authors: List<String>,
    val country: String,
    val name: String,
    val pages: Int
)