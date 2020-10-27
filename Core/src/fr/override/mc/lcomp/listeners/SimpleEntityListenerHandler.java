package fr.override.mc.lcomp.listeners;

import fr.override.mc.lcomp.wrappers.WrappedEntity;
import fr.override.mc.lcomp.wrappers.WrappedEntityEventDispatcher;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class SimpleEntityListenerHandler implements EntityListenerHandler {

    private final Map<UUID, WrappedEntityEventDispatcher> entities;

    public SimpleEntityListenerHandler(Plugin plugin) {
        this.entities = new HashMap<>();
        plugin.getServer()
                .getPluginManager()
                .registerEvents(new EntityEventCatcher(this::onEvent), plugin);
    }

    private void onEvent(EntityEvent event) {
        findDispatcher(event).ifPresent(dispatcher ->
                dispatcher.testEvent(event));
    }


    @Override
    public Optional<WrappedEntity> get(Entity entity) {
        WrappedEntityEventDispatcher dispatcher = entities.get(entity.getUniqueId());
        if (dispatcher == null)
            return Optional.empty();

        return Optional.ofNullable(dispatcher.getWrappedEntity());
    }

    @Override
    public void putIfAbsent(UUID id, WrappedEntityEventDispatcher dispatcher) {
        entities.putIfAbsent(id, dispatcher);
    }

    private Optional<WrappedEntityEventDispatcher> findDispatcher(EntityEvent event) {
        return Optional.ofNullable(entities.get(event.getEntity().getUniqueId()));
    }
}
