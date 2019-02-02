package com.apo.mobgengot.ui.categories

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.apo.mobgengot.R
import com.apo.mobgengot.databinding.HomeActivityBinding
import com.apo.mobgengot.domain.categories.Category
import org.koin.android.ext.android.setProperty
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity(), CategoriesViewModel.Listener {


    private val catViewModel: CategoriesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        val bindingActivity: HomeActivityBinding = DataBindingUtil.setContentView(
            this,
            R.layout.home_activity
        )

        setProperty(CategoriesViewModel.LISTENER_ID, this)
        this.lifecycle.addObserver(catViewModel)
        bindingActivity.model = catViewModel
    }

    /** **********************************
     *          Categories Listener
     *********************************** **/
    override fun onItemClick(category: Category) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** **********************************
     *          Companion
     *********************************** **/
    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

}
