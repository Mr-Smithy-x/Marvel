package com.smith.ufc.character

import com.smith.ufc.data.base.BaseContract
import com.smith.ufc.data.models.verbose.MarvelCharacterList
import com.smith.ufc.data.service.MarvelCharacterDataSource
import com.smith.ufc.data.service.MarvelDataSource
import io.reactivex.disposables.Disposable

/**
 * Created by Charlton on 10/27/17.
 */

class MarvelCharacterInteractor : CharacterContract.Interactor {
    override fun getComicCharacters(id: Int, marvelDataSource: CharacterRepository, callback: BaseContract.BaseInteractor.MarvelCallback<MarvelCharacterList>): Disposable {
        callback.onStarted()
        return marvelDataSource.getComicCharacters(id)
                .subscribe(
                        { res ->
                            run {
                                if (res.code == 200) {
                                    callback.onReceived(res.data)
                                }
                            }
                        },
                        { error -> run { callback.onError(error) } },
                        { callback.onCompleted() }
                )
    }

    override fun getCharacterById(id: Int, marvelDataSource: MarvelCharacterDataSource, callback: BaseContract.BaseInteractor.MarvelCallback<MarvelCharacterList>): Disposable {
        callback.onStarted()
        return marvelDataSource.getCharacter(id)
                .subscribe(
                        { res ->
                            run {
                                if (res.code == 200) {
                                    callback.onReceived(res.data)
                                }
                            }
                        },
                        { error -> run { callback.onError(error) } },
                        { callback.onCompleted() }
                )
    }


    override fun searchCharacterByName(name: String?, marvelDataSource: MarvelCharacterDataSource, callback: BaseContract.BaseInteractor.MarvelCallback<MarvelCharacterList>): Disposable {
        callback.onStarted()
        if (name != null) {
            return marvelDataSource.getCharacters(name)
                    .subscribe(
                            { res ->
                                run {
                                    if (res.code == 200) {
                                        callback.onReceived(res.data)
                                    }
                                }
                            },
                            { error -> run { callback.onError(error) } },
                            { callback.onCompleted() }
                    )
        } else {
            return marvelDataSource.characters
                    .subscribe(
                            { res ->
                                run {
                                    if (res.code == 200) {
                                        callback.onReceived(res.data)
                                    }
                                }
                            },
                            { error -> run { callback.onError(error) } },
                            { callback.onCompleted() }
                    )
        }
    }


}
