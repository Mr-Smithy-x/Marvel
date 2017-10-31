package com.smith.ufc.character

import com.smith.ufc.data.base.BaseContract
import com.smith.ufc.data.models.verbose.MarvelCharacterList
import com.smith.ufc.data.repositories.character.CharacterRepository
import com.smith.ufc.data.service.MarvelCharacterDataSource
import io.reactivex.disposables.Disposable

/**
 * Created by Charlton on 10/30/17.
 */

/**
 * @see MarvelCharacterInteractor
 * @see MarvelCharacterPresenter
 */
interface CharacterContract {

    interface View : BaseContract.BaseView<MarvelCharacterList> {

    }


    interface Presenter : BaseContract.BasePresenter<MarvelCharacterList> {

        fun getLatest()

        fun searchCharacterName(name: String)

        fun getCharacterById(int: Int)

        fun getComicCharacters(id: Int)

    }

    interface Interactor : BaseContract.BaseInteractor<MarvelCharacterList> {

        fun searchCharacterByName(name: String?, marvelDataSource: MarvelCharacterDataSource, callback: BaseContract.BaseInteractor.MarvelCallback<MarvelCharacterList>): Disposable

        fun getCharacterById(id: Int, marvelDataSource: MarvelCharacterDataSource, callback: BaseContract.BaseInteractor.MarvelCallback<MarvelCharacterList>): Disposable

        fun getComicCharacters(id: Int, marvelDataSource: CharacterRepository, marvelCallback: BaseContract.BaseInteractor.MarvelCallback<MarvelCharacterList>): Disposable

    }


}
