package com.esafirm.emvipi.view;

public interface MvpErrorView extends MvpView{
    void showError(Throwable throwable);
}
