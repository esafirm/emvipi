package com.esafirm.emvipi.view

interface MvpLoadingView : MvpView {
    fun showLoading(isShowLoading: Boolean, type: LoadingType)
}
