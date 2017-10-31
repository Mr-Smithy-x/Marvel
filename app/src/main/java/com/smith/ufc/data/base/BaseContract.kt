package com.smith.ufc.data.base

import com.smith.ufc.data.models.MarvelData
import com.smith.ufc.data.service.MarvelDataSource

import io.reactivex.disposables.Disposable

/**
 * Created by Charlton on 10/25/17.
 */

interface BaseContract {

    interface BaseView<T> {

        fun onFetchDataStarted()

        fun onFetchDataCompleted()

        fun onFetchDataSuccess(model: T)

        fun onFetchDataError(e: Throwable)
    }

    interface BasePresenter<T> {

        fun subscribe()

        fun unsubscribe()

        fun onDestroy()

    }

    /**
     * Created by Charlton on 10/27/17.
     */

    interface BaseInteractor<T> {


        interface MarvelCallback<T> {
            fun onReceived(model: MarvelData<T>)

            fun onError(error: Throwable)

            fun onCompleted()

            fun onStarted()
        }


    }
}
