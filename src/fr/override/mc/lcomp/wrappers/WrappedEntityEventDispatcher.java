package fr.override.mc.lcomp.wrappers;

import org.bukkit.event.entity.EntityEvent;

public interface WrappedEntityEventDispatcher {

    boolean testEvent(EntityEvent t);

    WrappedEntity getWrappedEntity();
}
