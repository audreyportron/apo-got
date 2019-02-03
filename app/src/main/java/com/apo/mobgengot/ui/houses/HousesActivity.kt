package com.apo.mobgengot.ui.houses

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.apo.mobgengot.R
import com.apo.mobgengot.domain.house.House
import com.apo.mobgengot.ui.book.BooksActivity
import com.apo.mobgengot.ui.book.BooksViewModel
import kotlinx.android.synthetic.main.houses_activity.*
import org.koin.android.ext.android.setProperty
import org.koin.androidx.viewmodel.ext.android.viewModel

class HousesActivity : AppCompatActivity() {

   val houseViewModel: HousesViewModel by viewModel()

    private lateinit var houseAdapter: HouseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.houses_activity)

        title = intent.extras[EXTRA_TITLE] as String
        val url = intent.extras[EXTRA_URL]
        setProperty(HousesViewModel.URL_ID, url)
        initAdapter()
    }

    private fun initAdapter(){
        val linearLayoutManager = LinearLayoutManager(this, VERTICAL,false)
        houseAdapter = HouseAdapter()
        house_recycler_view.apply {
            layoutManager = linearLayoutManager
            adapter = houseAdapter
        }
        houseViewModel.houseList.observe(this,
            Observer<PagedList<House>>{
            houseAdapter.submitList(it)
        })

        houses_item_name.apply{
            text = title
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                transitionName = intent.extras[EXTRA_TITLE_TRANSITION_NAME] as String
            }
        }



    }

    companion object {
        val EXTRA_URL = "EXTRA_URL"
        val EXTRA_TITLE = "EXTRA_TITLE"
        val EXTRA_TITLE_TRANSITION_NAME = "EXTRA_TITLE_TRANSITION_NAME"
        fun getIntent(context: Context, url: String, title: String, sharedView: TextView): Intent {
            return Intent(context, HousesActivity::class.java).apply {
                putExtra(EXTRA_URL, url)
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_TITLE_TRANSITION_NAME, ViewCompat.getTransitionName(sharedView))
            }
        }
    }
}
