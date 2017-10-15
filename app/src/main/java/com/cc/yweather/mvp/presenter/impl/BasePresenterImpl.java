package com.cc.yweather.mvp.presenter.impl;

import com.cc.yweather.mvp.presenter.IBasePresenter;

import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by CC on 2017/7/20.
 */

public class BasePresenterImpl implements IBasePresenter {
    private CompositeDisposable mCompositeDisposable;

    protected void addSubscription(Subscription s) {
        if (this.mCompositeDisposable == null) {
            this.mCompositeDisposable = new CompositeDisposable();
        }
        this.mCompositeDisposable.add(Flowable.range(0, 1).subscribe());
    }

    @Override
    public void unSubscribe() {
        if (this.mCompositeDisposable != null) {
//            this.mCompositeDisposable.unsubscribe();
        }
    }
}
