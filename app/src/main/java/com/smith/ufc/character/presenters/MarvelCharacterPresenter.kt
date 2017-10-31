package com.smith.ufc.character.presenters

import com.smith.ufc.Marvel
import com.smith.ufc.character.CharacterContract
import com.smith.ufc.data.base.BaseContract
import com.smith.ufc.data.models.MarvelData
import com.smith.ufc.data.models.verbose.MarvelCharacterList
import com.smith.ufc.data.repositories.character.CharacterRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Charlton on 10/25/17.
 */
class MarvelCharacterPresenter : CharacterContract.Presenter {


    @field:[Inject]
    lateinit var marvelDataSource: CharacterRepository

    @field:[Inject]
    lateinit var interactor: CharacterContract.Interactor


    private var disposable: CompositeDisposable = CompositeDisposable()

    private var view: CharacterContract.View? = null

    private var callback: BaseContract.BaseInteractor.MarvelCallback<MarvelCharacterList>? = null


    constructor(view: CharacterContract.View) {
        Marvel.instance.component!!.inject(this)
        this.view = view
        this.callback = object: BaseContract.BaseInteractor.MarvelCallback<MarvelCharacterList> {
            override fun onReceived(model: MarvelData<MarvelCharacterList>) {
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


    constructor(marvelDataSource: CharacterRepository, marvelInteractor: CharacterContract.Interactor, view: CharacterContract.View) {
        this.marvelDataSource = marvelDataSource
        this.interactor = marvelInteractor
        this.view = view
        this.callback = object: BaseContract.BaseInteractor.MarvelCallback<MarvelCharacterList> {
            override fun onReceived(model: MarvelData<MarvelCharacterList>) {
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

    constructor(marvelDataSource: CharacterRepository, marvelInteractor: CharacterContract.Interactor, view: CharacterContract.View, callback: BaseContract.BaseInteractor.MarvelCallback<MarvelCharacterList>) {
        this.marvelDataSource = marvelDataSource
        this.interactor = marvelInteractor
        this.view = view
        this.callback = callback
    }

    override fun getComicCharacters(id: Int) {
        disposable.add(interactor.getComicCharacters(id, marvelDataSource, this.callback!!))
    }

    override fun getLatest() {
        disposable.add(interactor.searchCharacterByName(null, marvelDataSource, this.callback!!))
    }

    override fun getCharacterById(int: Int) {
        disposable.add(interactor.getCharacterById(int, marvelDataSource, this.callback!!))
    }

    override fun searchCharacterName(name: String) {
        disposable.add(interactor.searchCharacterByName(name, marvelDataSource, this.callback!!))
    }

    override fun subscribe() {
        getLatest()
    }

    override fun unsubscribe() {
        disposable.clear()
    }

    override fun onDestroy() {
        view = null
    }
}
