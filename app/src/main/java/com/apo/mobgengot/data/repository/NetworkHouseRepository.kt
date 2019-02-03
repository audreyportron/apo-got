package com.apo.mobgengot.data.repository

import com.apo.mobgengot.data.network.CategoriesTypeApi
import com.apo.mobgengot.data.network.houses.toHouse
import com.apo.mobgengot.domain.house.House
import com.apo.mobgengot.domain.house.HouseRepository
import io.reactivex.Single

class NetworkHouseRepository(val api: CategoriesTypeApi) : HouseRepository {
    override fun getHouses(url: String, page: Int, limit: Int): Single<List<House>> {
        return api.getHouses(url, page, limit).map { houses ->
            houses.map { it.toHouse() }
        }
    }

}