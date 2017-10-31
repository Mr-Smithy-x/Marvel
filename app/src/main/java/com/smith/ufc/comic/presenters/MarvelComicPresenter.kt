package com.smith.ufc.comic.presenters

import com.smith.ufc.Marvel
import com.smith.ufc.comic.ComicContract
import com.smith.ufc.data.base.BaseContract
import com.smith.ufc.data.models.verbose.MarvelComicList
import com.smith.ufc.data.models.MarvelData
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

    constructor(view: ComicContract.View) {
        Marvel.instance.component!!.inject(this)
        this.view = view
        disposable = CompositeDisposable()
    }

    constructor(marvelDataSource: ComicRepository, marvelInteractor: ComicContract.Interactor, view: ComicContract.View) {
        this.marvelDataSource = marvelDataSource
        this.interactor = marvelInteractor
        this.view = view
        disposable = CompositeDisposable()
    }

    override fun getCharacterComics(id: Int) {
        disposable.add(interactor.getCharacterComics(id, marvelDataSource, object : BaseContract.BaseInteractor.MarvelCallback<MarvelComicList> {
            override fun onReceived(model: MarvelData<MarvelComicList>) {
                view!!.onFetchDataSuccess(model.results)
            }

            override fun onError(error: Throwable) {
                view!!.onFetchDataError(error)
            }

            override fun onCompleted() {
                view!!.onFetchDataCompleted()
            }

            override fun onStarted() {
                view!!.onFetchDataStarted()
            }
        }))
    }


    override fun getLatest() {
        disposable.add(interactor.searchComicTitle(null, marvelDataSource, object : BaseContract.BaseInteractor.MarvelCallback<MarvelComicList> {
            override fun onReceived(model: MarvelData<MarvelComicList>) {
                view!!.onFetchDataSuccess(model.results)
            }

            override fun onError(error: Throwable) {
                view!!.onFetchDataError(error)
            }

            override fun onCompleted() {
                view!!.onFetchDataCompleted()
            }

            override fun onStarted() {
                view!!.onFetchDataStarted()
            }
        }))
    }

    override fun getComicById(id: Int) {
        disposable.add(interactor.getComicById(id, marvelDataSource, object : BaseContract.BaseInteractor.MarvelCallback<MarvelComicList> {
            override fun onReceived(model: MarvelData<MarvelComicList>) {
                view!!.onFetchDataSuccess(model.results)
            }

            override fun onError(error: Throwable) {
                view!!.onFetchDataError(error)
            }

            override fun onCompleted() {
                view!!.onFetchDataCompleted()
            }

            override fun onStarted() {
                view!!.onFetchDataStarted()
            }
        }))
    }

    override fun searchComicByTitle(name: String) {
        disposable.add(interactor.searchComicTitle(name, marvelDataSource, object : BaseContract.BaseInteractor.MarvelCallback<MarvelComicList> {
            override fun onReceived(model: MarvelData<MarvelComicList>) {
                view!!.onFetchDataSuccess(model.results)
            }

            override fun onError(error: Throwable) {
                view!!.onFetchDataError(error)
            }

            override fun onCompleted() {
                view!!.onFetchDataCompleted()
            }

            override fun onStarted() {
                view!!.onFetchDataStarted()
            }
        }))
    }


    override fun subscribe() {

    }

    override fun unsubscribe() {

    }

    override fun onDestroy() {

    }
}
