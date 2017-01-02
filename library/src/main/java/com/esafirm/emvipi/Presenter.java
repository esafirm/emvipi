package com.esafirm.emvipi;

import com.esafirm.emvipi.view.MvpView;

public interface Presenter<V extends MvpView> {
    void detachView();
    void attachView(V view);
    V getView();
}
