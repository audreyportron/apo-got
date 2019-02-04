package com.apo.mobgengot.ui.characters

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.apo.mobgengot.domain.character.Character
import com.apo.mobgengot.domain.character.CharacterRepository
import com.apo.mobgengot.tools.AppSchedulers
import com.apo.mobgengot.ui.binding.AutobindedViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign

class CharactersViewModel(
    private val charactersRepository: CharacterRepository,
    private val url: String
) : ViewModel(), LifecycleObserver {
    companion object {
        const val URL_ID = "CharacterViewModel_url"
    }

    val charactersViewModel = ObservableArrayList<AutobindedViewModel>()
    val loading = ObservableBoolean(false)
    val error = ObservableBoolean(false)

    private val compositeDisposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getData() {
        compositeDisposable += charactersRepository.getCharacters(url)
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

    private fun onSuccess(characters: List<Character>) {
        charactersViewModel.clear()
        if (characters.isEmpty()) error.set(true)
        else {
            charactersViewModel.addAll(characters.map {
                CharacterItemViewModel(
                    character = it
                )
            })
            error.set(false)
        }
    }

    private fun onError(t: Throwable) {
        charactersViewModel.clear()
        error.set(true)
    }
}