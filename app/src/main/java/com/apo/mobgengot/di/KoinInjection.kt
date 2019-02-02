package com.apo.mobgengot.di

import androidx.room.Room
import com.apo.mobgengot.data.network.CategoriesApi
import com.apo.mobgengot.data.roomdb.GotDatabase
import com.google.gson.GsonBuilder
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/** **********************************
 *          Network Module
 *********************************** **/

fun createRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl("https://android.mobgen.com/test/api1/")
    .addConverterFactory(
        GsonConverterFactory.create(GsonBuilder().create())
    )
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()

val networkModule = module {
    single { createRetrofit().create(CategoriesApi::class.java) }
}

/** **********************************
 *          DB module
 *********************************** **/
val roomModule = module {
    single {
        Room.databaseBuilder(get(), GotDatabase::class.java, "app_got_db")
            .build()
    }
    //DAO
    single { get<GotDatabase>().categoriesDao() }
}

val roomTestModule = module {
    single {
        Room.inMemoryDatabaseBuilder(get(), GotDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
    //DAO
    single { get<GotDatabase>().categoriesDao() }
}