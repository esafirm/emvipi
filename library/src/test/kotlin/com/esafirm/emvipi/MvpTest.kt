package com.esafirm.emvipi

import com.nhaarman.mockito_kotlin.*
import org.junit.Test

class MvpTest {

    val presenter = TestPresenter(true)
    val view by lazy { mock<TestView>() }

    @Test(expected = IllegalStateException::class) fun presenterBeforeAttachViewTest() {
        presenter.loadData()
    }

    @Test fun presenterAfterAttachViewTest() {
        presenter.attachView(view)
        presenter.loadData()
    }

    @Test fun loadTest() {
        presenter.attachView(view)
        presenter.loadData()

        verify(view).showLoading(eq(true), any())
        verify(view).showContent(any())
        verify(view).showLoading(eq(false), any())

        presenter.loadError()
        verify(view).showError(any())
    }

    @Test fun nullObjectTest() {
        presenter.attachView(view)
        presenter.detachView()
        presenter.loadData()

        verify(view, never()).showContent(any())
    }

    @Test(expected = NullPointerException::class) fun nonNullObject(){
        val nonNullPresenter = TestPresenter(false)
        nonNullPresenter.attachView(view)
        nonNullPresenter.detachView()
        nonNullPresenter.loadData()
    }
}
