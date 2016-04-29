package com.badlogic.ashley.signals;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.SnapshotArray;

/**
 * A ComponentSignal is used to dispatch an event to multiple listeners.
 * @author Henrik Heine
 */
public class ComponentSignal<T extends Component> {
    private SnapshotArray<ComponentSignalReceiver<T>> listeners = new SnapshotArray<ComponentSignalReceiver<T>>();

    /** Add a Listener to this Signal
     * @param listener The Listener to be added */
    public void add(ComponentSignalReceiver<T> listener) { listeners.add(listener); }

    /** Remove a listener from this Signal
     * @param listener The Listener to remove */
    public void remove(ComponentSignalReceiver<T> listener) { listeners.removeValue(listener, true); }

    /** Removes all listeners attached to this {@link ComponentSignal}. */
    public void removeAllListeners() { listeners.clear(); }

    /** Dispatches an event to all Listeners registered to this Signal
     * @param entity The entity to send off
     * @param component The component to send off */
    @SuppressWarnings("unchecked")
    public void dispatch(Entity entity, T component) {
        final Object[] items = listeners.begin();
        for(int i = 0, n = listeners.size; i < n; i++) {
            ComponentSignalReceiver<T> listener = (ComponentSignalReceiver<T>)items[i];
            listener.receive(this, entity, component);
        }
        listeners.end();
    }
}
