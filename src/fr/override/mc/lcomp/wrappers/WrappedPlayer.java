package fr.override.mc.lcomp.wrappers;

import fr.override.mc.lcomp.ComponentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;

import java.util.function.Consumer;

public interface WrappedPlayer extends WrappedEntity {

    <T extends PlayerEvent> void onPlayerEvent(Class<T> classOfEvent, Consumer<T> eventConsumer);

    <T extends PlayerEvent> void onPlayerEvent(Class<T> classOfEvent, Consumer<T> eventConsumer, String id);

    void removeEventConsumers(String id);

    Player getPlayer();

    ComponentWrapper getWrapper();

}
