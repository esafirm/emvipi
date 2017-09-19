package com.esafirm.emvipi;

import android.support.annotation.NonNull;

import com.esafirm.emvipi.utils.NullObject;
import com.esafirm.emvipi.view.MvpView;

import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class AbsPresenter<V extends MvpView> implements Presenter<V> {

    private WeakReference<V> view;
    private V nullView;
    private Class<V> classOfView;

    private final boolean isUsingNullObject;

    public AbsPresenter() {
        this(true);
    }

    public AbsPresenter(boolean isUsingNullObject) {
        this.isUsingNullObject = isUsingNullObject;
        if (isUsingNullObject) {
            initViewClass();
        }
    }

    @Override
    public void attachView(@NonNull V view) {
        //noinspection unchecked
        this.view = new WeakReference<>(view);
        onViewAttached();
    }

    @Override
    public void detachView() {
        view = null;
        if (isUsingNullObject) {
            nullView = NullObject.INSTANCE.create(classOfView);
        }
        onViewDetached();
    }

    @Override
    public V getView() {
        if (view != null) {
            V realView = view.get();
            if (realView != null) {
                return realView;
            }
        }
        if (nullView == null && isUsingNullObject) {
            throw new IllegalStateException("You must call attachView first");
        }
        return nullView;
    }

    public boolean isViewAttached() {
        return view != null && nullView != null;
    }

    /* --------------------------------------------------- */
    /* > Helper */
    /* --------------------------------------------------- */

    private void initViewClass() {
        Type[] types = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
        for (Type type : types) {
            Class<?> genericType = (Class<?>) type;
            if (genericType.isInterface() && isSubTypeOfMvpView(genericType)) {
                //noinspection unchecked
                classOfView = (Class<V>) genericType;
            }
        }
    }

    /**
     * Scans the interface inheritnace hierarchy and checks if on the root is MvpView.class
     *
     * @param klass The leaf interface where to begin to scan
     * @return true if subtype of MvpView, otherwise false
     */
    private boolean isSubTypeOfMvpView(Class<?> klass) {
        if (klass.equals(MvpView.class)) {
            return true;
        }
        Class[] superInterfaces = klass.getInterfaces();
        for (Class superInterface : superInterfaces) {
            if (isSubTypeOfMvpView(superInterface)) {
                return true;
            }
        }
        return false;
    }

    /* --------------------------------------------------- */
    /* > Presenter Lifecycle */
    /* --------------------------------------------------- */

    protected void onViewAttached() {
    }

    protected void onViewDetached() {
    }
}
