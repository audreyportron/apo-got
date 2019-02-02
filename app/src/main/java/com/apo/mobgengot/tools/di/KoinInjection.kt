package com.apo.mobgengot.tools.di

import androidx.room.Room
import com.apo.mobgengot.data.network.CategoriesApi
import com.apo.mobgengot.data.repository.MainCategoriesRepository
import com.apo.mobgengot.data.roomdb.GotDatabase
import com.apo.mobgengot.domain.CategoriesRepository
import com.apo.mobgengot.domain.CategoriesService
import com.apo.mobgengot.ui.categories.CategoriesViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModule = module {
    viewModel<CategoriesViewModel> { CategoriesViewModel(get(), getProperty(CategoriesViewModel.LISTENER_ID)) }
}
val repositoryModule = module {
    single<CategoriesRepository> { MainCategoriesRepository(get(), get()) }
}

val serviceModule = module {
    single { CategoriesService(get()) }
}


/** **********************************
 *          Network Module
 *********************************** **/

fun createOkHttpClient(): OkHttpClient {
    val logInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    return OkHttpClient.Builder()
        .connectTimeout(30L, TimeUnit.SECONDS)
        .readTimeout(30L, TimeUnit.SECONDS)
        .addInterceptor(logInterceptor)
        .build()
}


fun createRetrofit(okHttp: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl("https://android.mobgen.com/test/api1/")
    .client(okHttp)
    .addConverterFactory(
        GsonConverterFactory.create(GsonBuilder().create())
    )
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()

val networkModule = module {
    single { createRetrofit(createOkHttpClient()).create(CategoriesApi::class.java) }
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