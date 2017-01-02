package com.esafirm.sample.emvipi;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.esafirm.emvipi.AbsPresenter;

public class MainPresenter extends AbsPresenter<MainView> {

    public void loadSomething() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(MainPresenter.class.getSimpleName(), "Loaded from presenter");
                getView().showContent("Hello from presenter");
            }
        }, 3000);
    }
}
