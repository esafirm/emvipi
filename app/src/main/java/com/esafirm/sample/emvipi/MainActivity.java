package com.esafirm.sample.emvipi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.esafirm.sample.R;

public class MainActivity extends Activity implements MainView, View.OnClickListener {

    private MainPresenter mainPresenter = new MainPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachView(this);
        mainPresenter.loadSomething();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detachView();
    }

    /* --------------------------------------------------- */
    /* > View Methods */
    /* --------------------------------------------------- */

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(boolean isShowLoading, int type) {
        findViewById(R.id.progress).setVisibility(isShowLoading
                ? View.VISIBLE
                : View.GONE);
    }

    @Override
    public void showContent(String s) {
        ((TextView) findViewById(R.id.text)).setText(s);
    }

    @Override
    public void onClick(View view) {

    }
}
