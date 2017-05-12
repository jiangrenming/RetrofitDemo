package com.example.jrm.retrofitdemo;

import android.app.Activity;

import rx.Completable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jrm on 2017-3-29.
 */

public abstract class BaseActivity extends Activity {

    private CompositeSubscription compositeSubscription;

    public void addSubscription(Subscription s) {
        if (this.compositeSubscription == null) {
            this.compositeSubscription = new CompositeSubscription();
        }
        this.compositeSubscription.add(s);
    }
}
