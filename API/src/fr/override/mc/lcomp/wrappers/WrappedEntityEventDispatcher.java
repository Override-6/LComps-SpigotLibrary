package fr.override.mc.lcomp.wrappers;

import org.bukkit.event.entity.EntityEvent;

public interface WrappedEntityEventDispatcher {
    /**
     * Dispatch the event to every registered actions
     *
     * @param event the event to dispatch
     * @return {@code true} if an action have been fired
     */
    boolean testEvent(EntityEvent event);

    /**
     * @return the wrapped component handled by this dispatcher
     */
    WrappedEntity getWrappedEntity();
}
