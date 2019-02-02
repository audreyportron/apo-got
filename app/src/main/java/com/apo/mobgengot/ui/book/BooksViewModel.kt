package com.apo.mobgengot.ui.book

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.apo.mobgengot.domain.book.Book
import com.apo.mobgengot.domain.book.BookRepository
import com.apo.mobgengot.tools.AppSchedulers
import com.apo.mobgengot.ui.binding.AutobindedViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign

class BooksViewModel(val repository: BookRepository, val url: String) : ViewModel(), LifecycleObserver {
    companion object {
        const val URL_ID = "BookViewModel_url"
    }

    val booksViewModel = ObservableArrayList<AutobindedViewModel>()
    val loading = ObservableBoolean(false)
    val error = ObservableBoolean(false)

    private val compositeDisposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getData() {
        compositeDisposable += repository.getBooks(url)
            .subscribeOn(AppSchedulers.io())
            .observeOn(AppSchedulers.mainThread())
            .doOnSubscribe { loading.set(true) }
            .doFinally { loading.set(false) }
            .subscribe(::onSuccess, ::onError)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun clear() {
        compositeDisposable.clear()
    }

    private fun onSuccess(books: List<Book>) {
        booksViewModel.clear()
        if (books.isEmpty()) error.set(true)
        else {
            booksViewModel.addAll(books.map {
                BookItemViewModel(
                    book = it
                )
            })
            error.set(false)
        }
    }

    private fun onError(t: Throwable) {
        booksViewModel.clear()
        error.set(true)
    }
}