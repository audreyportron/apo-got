package com.apo.mobgengot.ui.houses

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.apo.mobgengot.domain.house.House
import com.apo.mobgengot.domain.house.HouseRepository
import io.reactivex.disposables.CompositeDisposable

class HousesViewModel(
    private val repository: HouseRepository,
    private val url: String
) : ViewModel() {

    companion object {
        const val URL_ID = "HouseViewModel_url"
    }


    var houseList: LiveData<PagedList<House>>
    private val pageSize = 5
    private var sourceFactory: HouseDataSourceFactory
    private val compositeDisposable = CompositeDisposable()

    init {
        sourceFactory = HouseDataSourceFactory(repository, compositeDisposable, url)

        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize + 1)
            .setEnablePlaceholders(false)
            .build()
        houseList = LivePagedListBuilder<Int, House>(sourceFactory, config).build()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}