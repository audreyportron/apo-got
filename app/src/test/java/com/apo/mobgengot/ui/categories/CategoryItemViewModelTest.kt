package com.a

import com.apo.mobgengot.domain.Category
import com.apo.mobgengot.domain.CategoryType
import com.apo.mobgengot.ui.categories.CategoryItemViewModel
import org.junit.Assert.assertTrue
import org.junit.Test

class CategoryItemViewModelTest {
    @Test
    fun should_display_item_title() {
        //Given
        val category = Category(id = 1, title = "title1", type = CategoryType.BOOKS, apiLink = "href1")

        //When
        val model = CategoryItemViewModel(category, {})

        //Then
        assertTrue(model.title == category.title)
    }
}