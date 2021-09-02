package com.nan.xarch.eventbus.core;

import androidx.lifecycle.Observer;

import com.nan.xarch.util.reflect.ReflectHelper;

public class EventObserverWrapper<T> implements Observer<T> {

    private static final int START_VERSION = -1;
    private Observer<? super T> mObserverDelegate;
    private boolean mPreventNextEvent = false;

    public EventObserverWrapper(EventLiveData<T> liveData, boolean sticky, Observer<? super T> observer) {
        this.mObserverDelegate = observer;
        if (!sticky) {
            int version = START_VERSION;
            try {
                version = (int) ReflectHelper.of(liveData).getField("mVersion");
            } catch (Exception e) {
                e.printStackTrace();
            }
            mPreventNextEvent = version > START_VERSION;
        }
    }

    @Override
    public void onChanged(T t) {
        if (mPreventNextEvent) {
            mPreventNextEvent = false;
            return;
        }
        mObserverDelegate.onChanged(t);
    }
}
