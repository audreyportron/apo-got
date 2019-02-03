package com.apo.mobgengot.domain.house

import io.reactivex.Single

interface HouseRepository{
    fun getHouses(url:String, page:Int, limit:Int): Single<List<House>>
}