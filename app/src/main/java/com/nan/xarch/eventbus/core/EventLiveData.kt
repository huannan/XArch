package com.nan.xarch.eventbus.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class EventLiveData<T> : MutableLiveData<T>() {

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
    }

    fun observe(owner: LifecycleOwner, sticky: Boolean, observer: Observer<in T>) {
        super.observe(owner, wrapObserver(sticky, observer))
    }

    override fun observeForever(observer: Observer<in T>) {
        super.observeForever(observer)
    }

    fun observeForever(sticky: Boolean, observer: Observer<in T>) {
        super.observeForever(wrapObserver(sticky, observer))
    }

    private fun wrapObserver(sticky: Boolean, observer: Observer<in T>): Observer<T> {
        return EventObserverWrapper(this, sticky, observer)
    }
}