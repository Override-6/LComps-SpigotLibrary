package fr.override.mc.lcomp.wrappers;

import fr.override.mc.lcomp.ComponentWrapper;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityEvent;

import java.util.function.Consumer;

public interface WrappedEntity {

    /**
     * @param eventClass    the kind of event to listen
     * @param consumerEvent the action to perform
     * @param <T>           the type of event
     */
    <T extends EntityEvent> void onEntityEvent(Class<T> eventClass, Consumer<T> consumerEvent);

    /**
     * @param eventClass    the kind of event to listen
     * @param consumerEvent the action to perform
     * @param id            the identifier of the eveXnt
     * @param <T>           the type of event
     */
    <T extends EntityEvent> void onEntityEvent(Class<T> eventClass, Consumer<T> consumerEvent, String id);

    /**
     * Unregisters any event action bound to an id
     *
     * @param id the identifier
     */
    void removeEventConsumers(String id);

    /**
     * @return the bukkit entity
     */
    Entity getEntity();

    /**
     * @return the {@link ComponentWrapper} that handles this wrapper
     */
    ComponentWrapper getWrapper();

}
