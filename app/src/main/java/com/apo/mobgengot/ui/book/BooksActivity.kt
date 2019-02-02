package com.apo.mobgengot.ui.book

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.apo.mobgengot.R
import com.apo.mobgengot.databinding.BooksActivityBinding
import org.koin.android.ext.android.setProperty
import org.koin.androidx.viewmodel.ext.android.viewModel

class BooksActivity : AppCompatActivity() {

    private val booksViewModel: BooksViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.books_activity)
        val bindingActivity: BooksActivityBinding = DataBindingUtil.setContentView(
            this,
            R.layout.books_activity
        )
        title = intent.extras[EXTRA_TITLE] as String
        val url = intent.extras[EXTRA_URL]
        setProperty(BooksViewModel.URL_ID, url)
        this.lifecycle.addObserver(booksViewModel)
        bindingActivity.model = booksViewModel
    }

    companion object {

        val EXTRA_URL = "EXTRA_URL"
        val EXTRA_TITLE = "EXTRA_TITLE"
        fun getIntent(context: Context, url: String, title: String): Intent {
            return Intent(context, BooksActivity::class.java).apply {
                putExtra(EXTRA_URL, url)
                putExtra(EXTRA_TITLE, title)
            }
        }
    }
}
