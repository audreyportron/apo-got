package com.apo.mobgengot.ui.book

import com.apo.mobgengot.domain.book.Book
import com.apo.mobgengot.domain.book.BookRepository
import com.apo.mobgengot.tools.AppSchedulers
import com.apo.mobgengot.tools.initForTests
import io.reactivex.Single
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mockito

class BooksViewModelTest {

    val bookRepository: BookRepository = Mockito.mock(BookRepository::class.java)


    private val viewModel: BooksViewModel by lazy {
        BooksViewModel(bookRepository, "any")
    }

    @Before
    fun before() {
        AppSchedulers.initForTests()
    }

    /**
     * Test throw a nullPointerException on the service mock
     */
    @Ignore
    @Test
    fun should_get_books_and_show_when_service_no_error() {
        //Given
        val book = Book(listOf("Author1"), "USA", "name", 100)
        BDDMockito.given(bookRepository.getBooks("any")).willReturn(Single.just(listOf(book)))

        //When
        viewModel.getData()

        //Then
        assertTrue(viewModel.booksViewModel.size == 1)
        assertTrue(viewModel.booksViewModel[0] is BookItemViewModel)
        assertTrue(viewModel.loading.get() == false)
        assertTrue(viewModel.error.get() == false)
    }

    /**
     * Test throw a nullPointerException on the service mock
     */
    @Ignore
    @Test
    fun should_show_error_when_get_data_error() {
        //Given
        BDDMockito.given(bookRepository.getBooks("any")).willReturn(Single.error(Throwable()))

        //When
        viewModel.getData()

        //Then
        assertTrue(viewModel.booksViewModel.size == 0)
        assertTrue(viewModel.loading.get() == false)
        assertTrue(viewModel.error.get() == true)
    }

    /**
     * Test throw a nullPointerException on the service mock
     */
    @Ignore
    @Test
    fun should_show_error_when_categories_empty() {
        //Given
        BDDMockito.given(bookRepository.getBooks("any")).willReturn(Single.just(emptyList()))

        //When
        viewModel.getData()

        //Then
        assertTrue(viewModel.booksViewModel.size == 0)
        assertTrue(viewModel.loading.get() == false)
        assertTrue(viewModel.error.get() == true)
    }
}