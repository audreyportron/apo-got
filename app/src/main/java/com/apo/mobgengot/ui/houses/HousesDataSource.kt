package com.apo.mobgengot.ui.houses

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.apo.mobgengot.domain.house.House
import com.apo.mobgengot.domain.house.HouseRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign

class HousesDataSource(
    private val repository: HouseRepository,
    private val compositeDisposable: CompositeDisposable,
    private val url: String
) : PageKeyedDataSource<Int, House>() {


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, House>) {
        compositeDisposable += repository.getHouses(url, 1, params.requestedLoadSize)
            .subscribe({ house -> callback.onResult(house, 0, 2) }, {})
    }


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, House>) {
        compositeDisposable += repository.getHouses(url, params.key, params.requestedLoadSize)
            .subscribe({ house -> callback.onResult(house, (params.key + 1)) }, {})

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, House>) {
    }

}

class HouseDataSourceFactory(
    private val repository: HouseRepository,
    private val compositeDisposable: CompositeDisposable,
    private val url: String
) : DataSource.Factory<Int, House>() {

    val houseDataSourceLiveData = MutableLiveData<HousesDataSource>()

    override fun create(): DataSource<Int, House> {
        val housesDataSource = HousesDataSource(repository, compositeDisposable, url)
        houseDataSourceLiveData.postValue(housesDataSource)
        return housesDataSource
    }

}