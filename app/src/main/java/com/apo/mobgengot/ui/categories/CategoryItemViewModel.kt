package com.apo.mobgengot.ui.categories

import android.view.View
import com.apo.mobgengot.BR
import com.apo.mobgengot.R
import com.apo.mobgengot.domain.Category
import com.apo.mobgengot.ui.binding.AutobindedViewModel

class CategoryItemViewModel(
    private val category: Category,
    private val onItemClick: (Category) -> Unit,
    override val layout: Int = R.layout.home_category_item,
    override val variable: Int = BR.model
) : AutobindedViewModel {
    val title = category.title
    fun onItemClick(v: View) {
        onItemClick.invoke(category)
    }
}
