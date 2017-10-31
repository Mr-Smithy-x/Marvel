package com.smith.ufc.comic.presenters

import com.smith.ufc.Marvel
import com.smith.ufc.comic.ComicContract
import com.smith.ufc.data.base.BaseContract
import com.smith.ufc.data.models.verbose.MarvelComicList
import com.smith.ufc.data.models.MarvelData
import com.smith.ufc.data.models.verbose.MarvelCharacterList
import com.smith.ufc.data.repositories.comic.ComicRepository

import javax.inject.Inject

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Charlton on 10/28/17.
 */

class MarvelComicPresenter : ComicContract.Presenter {


    @field:[Inject]
    lateinit var marvelDataSource: ComicRepository

    @field:[Inject]
    lateinit var interactor: ComicContract.Interactor


    private var disposable: CompositeDisposable

    private var view: ComicContract.View? = null

    private var callback: BaseContract.BaseInteractor.MarvelCallback<MarvelComicList>? = null

    constructor(view: ComicContract.View) {
        Marvel.instance.component!!.inject(this)
        this.view = view
        disposable = CompositeDisposable()
        this.callback = object: BaseContract.BaseInteractor.MarvelCallback<MarvelComicList> {
            override fun onReceived(model: MarvelData<MarvelComicList>) {
                view.onFetchDataSuccess(model.results)
            }

            override fun onError(error: Throwable) {
                view.onFetchDataError(error)
            }

            override fun onCompleted() {
                view.onFetchDataCompleted()
            }

            override fun onStarted() {
                view.onFetchDataStarted()
            }
        }
    }

    constructor(marvelDataSource: ComicRepository, marvelInteractor: ComicContract.Interactor, view: ComicContract.View, callback: BaseContract.BaseInteractor.MarvelCallback<MarvelComicList>) {
        this.marvelDataSource = marvelDataSource
        this.interactor = marvelInteractor
        this.view = view
        this.callback = callback
        disposable = CompositeDisposable()
    }

    override fun getCharacterComics(id: Int) {
        disposable.add(interactor.getCharacterComics(id, marvelDataSource, this.callback!!))
    }


    override fun getLatest() {
        disposable.add(interactor.searchComicTitle(null, marvelDataSource, this.callback!!))
    }

    override fun getComicById(id: Int) {
        disposable.add(interactor.getComicById(id, marvelDataSource, this.callback!!))
    }

    override fun searchComicByTitle(name: String) {
        disposable.add(interactor.searchComicTitle(name, marvelDataSource, this.callback!!))
    }


    override fun subscribe() {

    }

    override fun unsubscribe() {

    }

    override fun onDestroy() {
        view = null
    }
}
