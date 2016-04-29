package com.badlogic.ashley.signals;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.HashMap;

@SuppressWarnings("unchecked")
public class ComponentSignalMap {
    private HashMap<Class<? extends Component>, ComponentSignal<?>> listenerMap = new HashMap<Class<? extends Component>, ComponentSignal<?>>();

    public <T extends Component> void addListener(Class<T> componentClass, ComponentSignalReceiver<T> listener) {
        ComponentSignal<T> signal = (ComponentSignal<T>)listenerMap.get(componentClass);
        if(signal == null) {
            signal = new ComponentSignal<T>();
            listenerMap.put(componentClass, signal);
        }
        signal.add(listener);
    }

    public <T extends Component> void removeListener(Class<T> componentClass, ComponentSignalReceiver<T> listener) {
        ComponentSignal<T> signal = (ComponentSignal<T>)listenerMap.get(componentClass);
        if(signal != null) signal.remove(listener);
    }

    public void clear() { listenerMap.clear(); }

    public <T extends Component> void dispatch(Class<T> componentClass, Entity entity, Component component) {
        ComponentSignal<T> signal = (ComponentSignal<T>)listenerMap.get(componentClass);
        if(signal != null) signal.dispatch(entity, (T)component);
    }
}
