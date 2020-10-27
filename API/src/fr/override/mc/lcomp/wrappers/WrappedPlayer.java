package fr.override.mc.lcomp.wrappers;

import fr.override.mc.lcomp.ComponentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;

import java.util.function.Consumer;

public interface WrappedPlayer extends WrappedEntity {

    /**
     * @param classOfEvent the kind of event to listen
     * @param eventConsumer   the action to perform
     * @param <T>        the type of event
     */
    <T extends PlayerEvent> void onPlayerEvent(Class<T> classOfEvent, Consumer<T> eventConsumer);

    /**
     * @param classOfEvent the kind of event to listen
     * @param eventConsumer   the action to perform
     * @param id         the identifier of the event
     * @param <T>        the type of event
     */
    <T extends PlayerEvent> void onPlayerEvent(Class<T> classOfEvent, Consumer<T> eventConsumer, String id);

    /**
     * Unregisters any event action bound to an id
     * @param id the identifier
     */
    void removeEventConsumers(String id);

    /**
     * @return the bukkit player
     */
    Player getPlayer();


    /**
     * @return the {@link ComponentWrapper} that handles this wrapper
     */
    ComponentWrapper getWrapper();

}
