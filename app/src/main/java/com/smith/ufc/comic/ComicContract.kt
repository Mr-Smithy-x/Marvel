package com.smith.ufc.comic

import com.smith.ufc.data.base.BaseContract
import com.smith.ufc.data.models.verbose.MarvelComicList
import com.smith.ufc.data.service.MarvelComicDataSource
import io.reactivex.disposables.Disposable

/**
 * Created by Charlton on 10/30/17.
 */

/**
 * @see MarvelComicPresenter
 * @see MarvelComicInteractor
 */
interface ComicContract {

    interface View : BaseContract.BaseView<MarvelComicList> {

    }


    interface Presenter : BaseContract.BasePresenter<MarvelComicList> {

        fun getLatest()

        fun searchComicByTitle(name: String)

        fun getComicById(int: Int)

        fun getCharacterComics(int: Int)

    }

    interface Interactor : BaseContract.BaseInteractor<MarvelComicList> {

        fun searchComicTitle(name: String?, marvelDataSource: MarvelComicDataSource, callback: BaseContract.BaseInteractor.MarvelCallback<MarvelComicList>): Disposable

        fun getComicById(id: Int, marvelDataSource: MarvelComicDataSource, callback: BaseContract.BaseInteractor.MarvelCallback<MarvelComicList>): Disposable

        fun getCharacterComics(id: Int, marvelDataSource: MarvelComicDataSource, callback: BaseContract.BaseInteractor.MarvelCallback<MarvelComicList>): Disposable



    }


}
