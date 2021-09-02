package com.nan.xarch.eventbus;

import androidx.collection.ArrayMap;

import com.nan.xarch.eventbus.core.EventLiveData;

import java.util.Map;

public class XEventBus {

    private final Map<String, EventLiveData<?>> mChannels;

    private XEventBus() {
        mChannels = new ArrayMap<>();
    }

    private static class LiveDataBusHolder {
        private static final XEventBus INSTANCE = new XEventBus();
    }

    private static XEventBus getInstance() {
        return LiveDataBusHolder.INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public static synchronized <T> EventLiveData<T> with(String eventName, Class<T> type) {
        Map<String, EventLiveData<?>> channels = getChannels();
        if (!channels.containsKey(eventName)) {
            channels.put(eventName, new EventLiveData<T>());
        }
        return (EventLiveData<T>) channels.get(eventName);
    }

    @SuppressWarnings("unchecked")
    public static <T> void post(String eventName, T value) {
        EventLiveData<T> channel = (EventLiveData<T>) with(eventName, value.getClass());
        channel.postValue(value);
    }

    private static Map<String, EventLiveData<?>> getChannels() {
        return getInstance().mChannels;
    }
}
