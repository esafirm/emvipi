package com.esafirm.emvipi

import com.esafirm.emvipi.view.MvpView

interface Presenter<V : MvpView> {
    fun detachView()
    fun attachView(view: V)
    val view: V
}
