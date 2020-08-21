package fr.override.mc.lcomp.listeners;

import fr.override.mc.lcomp.wrappers.WrappedEntity;
import fr.override.mc.lcomp.wrappers.WrappedEntityEventDispatcher;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;

import java.util.Optional;
import java.util.UUID;

public interface EntitiesListener extends Listener {

    Optional<WrappedEntity> get(Entity entity);

    void putIfAbsent(UUID id, WrappedEntityEventDispatcher dispatcher);

}
