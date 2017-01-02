package com.esafirm.emvipi

import com.esafirm.emvipi.view.LoadingType

class TestPresenter(val nullObject: Boolean) : AbsPresenter<TestView>(nullObject) {

    fun loadData() {
        view.showLoading(true, LoadingType.ANY)
        view.showContent(TestData("test"))
        view.showLoading(false, LoadingType.ANY)
    }

    fun loadError() {
        view.showError(IllegalStateException("test"))
    }
}
