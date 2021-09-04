package com.nan.xarch.eventbus

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.nan.xarch.eventbus.core.EventLiveData

object XEventBus {

    private val channels = HashMap<String, EventLiveData<*>>()

    private fun <T> with(eventName: String): EventLiveData<T> {
        synchronized(channels) {
            if (!channels.containsKey(eventName)) {
                channels[eventName] = EventLiveData<T>()
            }
            return (channels[eventName] as EventLiveData<T>)
        }
    }

    fun <T> post(eventName: String, message: T) {
        val eventLiveData = with<T>(eventName)
        eventLiveData.postValue(message!!)
    }

    fun <T> observe(owner: LifecycleOwner, eventName: String, observer: Observer<T>) {
        with<T>(eventName).observe(owner, observer)
    }

    fun <T> observe(owner: LifecycleOwner, eventName: String, sticky: Boolean, observer: Observer<T>) {
        with<T>(eventName).observe(owner, sticky, observer)
    }

    fun <T> observeForever(eventName: String, observer: Observer<T>) {
        with<T>(eventName).observeForever(observer)
    }

    fun <T> observeForever(eventName: String, sticky: Boolean, observer: Observer<T>) {
        with<T>(eventName).observeForever(sticky, observer)
    }
}