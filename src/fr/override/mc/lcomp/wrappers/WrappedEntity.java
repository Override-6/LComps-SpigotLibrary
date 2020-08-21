package fr.override.mc.lcomp.wrappers;

import fr.override.mc.lcomp.ComponentWrapper;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.function.Consumer;

public interface WrappedEntity {

    <T extends EntityEvent> void onEntityEvent(Class<T> eventClass, Consumer<T> consumerEvent);

    <T extends EntityEvent> void onEntityEvent(Class<T> eventClass, Consumer<T> consumerEvent, String id);

    void removeEventConsumers(String id);

    Entity getEntity();

    ComponentWrapper getWrapper();

}
