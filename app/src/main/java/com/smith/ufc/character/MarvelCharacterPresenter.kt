package com.smith.ufc.character

import com.smith.ufc.Marvel
import com.smith.ufc.data.base.BaseContract
import com.smith.ufc.data.models.verbose.MarvelCharacterList
import com.smith.ufc.data.models.MarvelData
import com.smith.ufc.data.di.MarvelModule
import com.smith.ufc.data.service.MarvelDataSource
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Charlton on 10/25/17.
 */
class MarvelCharacterPresenter : CharacterContract.Presenter {


    @field:[Inject]
    lateinit var marvelDataSource: CharacterRepository

    @field:[Inject]
    lateinit var interactor: CharacterContract.Interactor


    private var disposable: CompositeDisposable

    private var view: CharacterContract.View? = null


    constructor(view: CharacterContract.View) {
        Marvel.instance.component!!.inject(this)
        this.view = view
        disposable = CompositeDisposable()
    }

    constructor(marvelDataSource: CharacterRepository, marvelInteractor: CharacterContract.Interactor, view: CharacterContract.View) {
        this.marvelDataSource = marvelDataSource
        this.interactor = marvelInteractor
        this.view = view
        disposable = CompositeDisposable()
    }

    override fun getComicCharacters(id: Int) {
        disposable.add(interactor.getComicCharacters(id, marvelDataSource, object : BaseContract.BaseInteractor.MarvelCallback<MarvelCharacterList> {
            override fun onReceived(model: MarvelData<MarvelCharacterList>) {
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
        disposable.add(interactor.searchCharacterByName(null, marvelDataSource, object : BaseContract.BaseInteractor.MarvelCallback<MarvelCharacterList> {
            override fun onReceived(model: MarvelData<MarvelCharacterList>) {
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

    override fun getCharacterById(int: Int) {
        disposable.add(interactor.getCharacterById(int, marvelDataSource, object : BaseContract.BaseInteractor.MarvelCallback<MarvelCharacterList> {
            override fun onReceived(model: MarvelData<MarvelCharacterList>) {
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

    override fun searchCharacterName(name: String) {
        disposable.add(interactor.searchCharacterByName(name, marvelDataSource, object : BaseContract.BaseInteractor.MarvelCallback<MarvelCharacterList> {
            override fun onReceived(model: MarvelData<MarvelCharacterList>) {
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
        getLatest()
    }

    override fun unsubscribe() {
        disposable.clear()
    }

    override fun onDestroy() {
        this.view = null
    }
}
