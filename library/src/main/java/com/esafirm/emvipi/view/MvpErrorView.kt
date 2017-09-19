package com.esafirm.emvipi.view

interface MvpErrorView : MvpView {
    fun showError(throwable: Throwable)
}
