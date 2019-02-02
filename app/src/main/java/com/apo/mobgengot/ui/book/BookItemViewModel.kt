package com.apo.mobgengot.ui.book

import com.apo.mobgengot.BR
import com.apo.mobgengot.R
import com.apo.mobgengot.domain.book.Book
import com.apo.mobgengot.ui.binding.AutobindedViewModel

class BookItemViewModel(
    private val book: Book,
    override val layout: Int = R.layout.book_item,
    override val variable: Int = BR.model
) : AutobindedViewModel {
    val title = book.name
    val mainAuthor = book.authors.first()
    val country = book.country
    val pagesArg = arrayOf(book.pages.toString())
    val pagesResId = R.string.book_pages
}
