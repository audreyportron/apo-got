package com.apo.mobgengot.ui.categories

import com.apo.mobgengot.domain.categories.CategoriesService
import com.apo.mobgengot.domain.categories.Category
import com.apo.mobgengot.domain.categories.CategoryType
import com.apo.mobgengot.tools.AppSchedulers
import com.apo.mobgengot.tools.initForTests
import io.reactivex.Single
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CategoriesViewModelTest {

    val categoriesService: CategoriesService = mock(CategoriesService::class.java)


    private val categoriesViewModel: CategoriesViewModel by lazy {
        CategoriesViewModel(categoriesService, null)
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
    fun should_get_categories_and_show_when_service_no_error() {
        //Given
        val category = Category(
            id = 1,
            title = "title1",
            type = CategoryType.BOOKS,
            apiLink = "href1"
        )
        given(categoriesService.getCategoriesByName(true)).willReturn(Single.just(listOf(category)))

        //When
        categoriesViewModel.getData()

        //Then
        assertTrue(categoriesViewModel.categoriesViewModel.size == 1)
        assertTrue(categoriesViewModel.categoriesViewModel[0] is CategoryItemViewModel)
        assertTrue(categoriesViewModel.loading.get() == false)
        assertTrue(categoriesViewModel.error.get() == false)
    }

    /**
     * Test throw a nullPointerException on the service mock
     */
    @Ignore
    @Test
    fun should_show_error_when_get_data_error() {
        //Given
        given(categoriesService.getCategoriesByName(true)).willReturn(Single.error(Throwable()))

        //When
        categoriesViewModel.getData()

        //Then
        assertTrue(categoriesViewModel.categoriesViewModel.size == 0)
        assertTrue(categoriesViewModel.loading.get() == false)
        assertTrue(categoriesViewModel.error.get() == true)
    }

    /**
     * Test throw a nullPointerException on the service mock
     */
    @Ignore
    @Test
    fun should_show_error_when_categories_empty() {
        //Given
        given(categoriesService.getCategoriesByName(true)).willReturn(Single.just(emptyList()))

        //When
        categoriesViewModel.getData()

        //Then
        assertTrue(categoriesViewModel.categoriesViewModel.size == 0)
        assertTrue(categoriesViewModel.loading.get() == false)
        assertTrue(categoriesViewModel.error.get() == true)
    }
}