package com.smith.ufc.comic

import com.smith.ufc.data.base.BaseContract
import com.smith.ufc.data.models.verbose.MarvelComicList
import com.smith.ufc.data.service.MarvelComicDataSource
import io.reactivex.disposables.Disposable

/**
 * Created by Charlton on 10/28/17.
 */

class MarvelComicInteractor : ComicContract.Interactor {
    override fun getCharacterComics(id: Int, marvelDataSource: MarvelComicDataSource, callback: BaseContract.BaseInteractor.MarvelCallback<MarvelComicList>): Disposable {
        callback.onStarted()
        return marvelDataSource.getCharacterComics(id).subscribe(
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

    override fun getComicById(id: Int, marvelDataSource: MarvelComicDataSource, callback: BaseContract.BaseInteractor.MarvelCallback<MarvelComicList>): Disposable {
        callback.onStarted()
        return marvelDataSource.getComic(id).subscribe(
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

    override fun searchComicTitle(name: String?, marvelDataSource: MarvelComicDataSource, callback: BaseContract.BaseInteractor.MarvelCallback<MarvelComicList>): Disposable {
        callback.onStarted()
        if (name != null) {
            return marvelDataSource.getComics(name).subscribe(
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
            return marvelDataSource.comics.subscribe(
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
