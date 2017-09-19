package com.esafirm.emvipi

import com.esafirm.emvipi.view.ANY

class TestPresenter(val nullObject: Boolean) : AbsPresenter<TestView>(nullObject) {

    fun loadData() {
        view.showLoading(true, ANY)
        view.showContent(TestData("test"))
        view.showLoading(false, ANY)
    }

    fun loadError() {
        view.showError(IllegalStateException("test"))
    }
}
