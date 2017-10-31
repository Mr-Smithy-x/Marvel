package com.smith.ufc.data.base

import java.util.*

/**
 * Created by Charlton on 10/27/17.
 */

abstract class BaseAdapterPresenter<in V : BaseAdapterPresenter.BaseView, I> {


    private var repository: MutableList<I>? = ArrayList()

    val repositoriesRowsCount: Int get() = if (repository == null) 0 else repository!!.size

    fun getItem(index: Int): I = repository!![index]

    fun getItems() : MutableList<I>? = repository

    fun setData(data: ArrayList<I>) {
        this.repository = data
    }

    fun setData(data: List<I?>)
    {
        this.repository?.clear()
        for (i in data){
            this.repository?.add(i!!)
        }
    }

    fun clear() {
        if (repository != null) repository!!.clear()
    }

    abstract fun getItemId() : MutableList<Int>

    abstract fun onBindRepositoryRowViewAtPosition(position: Int, view: V)


    /**
     * Created by Charlton on 10/27/17.
     */

    interface BaseView

}
