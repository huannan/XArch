package com.nan.xarch.eventbus.core;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class EventLiveData<T> extends MutableLiveData<T> {

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        this.observe(owner, false, observer);
    }

    @Override
    public void observeForever(@NonNull Observer<? super T> observer) {
        this.observeForever(false, observer);
    }

    public void observe(@NonNull LifecycleOwner owner, boolean sticky, @NonNull Observer<? super T> observer) {
        super.observe(owner, wrapObserver(sticky, observer));
    }

    public void observeForever(boolean sticky, @NonNull Observer<? super T> observer) {
        super.observeForever(wrapObserver(sticky, observer));
    }

    private Observer<T> wrapObserver(boolean sticky, Observer<? super T> observer) {
        return new EventObserverWrapper<>(this, sticky, observer);
    }

}