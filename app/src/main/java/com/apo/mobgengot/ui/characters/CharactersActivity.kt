package com.apo.mobgengot.ui.characters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.apo.mobgengot.R
import com.apo.mobgengot.databinding.CharactersActivityBinding
import org.koin.android.ext.android.setProperty
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersActivity : AppCompatActivity() {

    private val charactersViewModel: CharactersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.characters_activity)
        val bindingActivity: CharactersActivityBinding = DataBindingUtil.setContentView(
            this,
            R.layout.characters_activity
        )
        title = intent.extras[EXTRA_TITLE] as String
        val url = intent.extras[EXTRA_URL]
        setProperty(CharactersViewModel.URL_ID, url)
        this.lifecycle.addObserver(charactersViewModel)
        bindingActivity.model = charactersViewModel
    }

    companion object {

        val EXTRA_URL = "EXTRA_URL"
        val EXTRA_TITLE = "EXTRA_TITLE"
        fun getIntent(context: Context, url: String, title: String): Intent {
            return Intent(context, CharactersActivity::class.java).apply {
                putExtra(EXTRA_URL, url)
                putExtra(EXTRA_TITLE, title)
            }
        }
    }
}
