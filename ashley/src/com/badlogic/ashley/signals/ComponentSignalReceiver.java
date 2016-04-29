package com.badlogic.ashley.signals;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

/**
 * A simple Listener interface used to listen to a {@link ComponentSignal}.
 * @author Henrik Heine
 */
public interface ComponentSignalReceiver<T extends Component> {
    /**
     * @param signal The {@link ComponentSignal} that triggered the event
     * @param entity The {@link Entity} the component was added to
     * @param component The {@link Component} that was added
     */
    void receive(ComponentSignal<T> signal, Entity entity, T component);
}
