package com.apo.mobgengot.ui.houses

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        title = intent.extras[HousesActivity.EXTRA_TITLE] as String
        val url = intent.extras[HousesActivity.EXTRA_URL]
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

    }

    companion object {
        val EXTRA_URL = "EXTRA_URL"
        val EXTRA_TITLE = "EXTRA_TITLE"
        fun getIntent(context: Context, url: String, title: String): Intent {
            return Intent(context, HousesActivity::class.java).apply {
                putExtra(EXTRA_URL, url)
                putExtra(EXTRA_TITLE, title)
            }
        }
    }
}
