package fr.override.mc.lcomp.wrappers;

import fr.override.mc.lcomp.ComponentWrapper;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SimpleEntityListener implements EntityListener {

    private final Entity entity;
    private final List<EventConsumer<? extends EntityEvent>> eventConsumers;
    private final ComponentWrapper wrapper;

    public SimpleEntityListener(Entity entity, ComponentWrapper wrapper) {
        this.entity = entity;
        this.eventConsumers = new ArrayList<>();
        this.wrapper = wrapper;
    }

    @Override
    public <T extends EntityEvent> void onEntityEvent(Class<T> eventClass, Consumer<T> consumerEvent) {
        onEntityEvent(eventClass, consumerEvent, "");
    }

    @Override
    public <T extends EntityEvent> void onEntityEvent(Class<T> eventClass, Consumer<T> consumerEvent, String id) {
        eventConsumers.add(new EventConsumer<>(eventClass, consumerEvent, id));
    }

    @Override
    public void removeEventConsumers(String id) {
        eventConsumers.removeIf(eventConsumer -> eventConsumer.getId().equals(id));
    }

    @Override
    public Entity getEntity() {
        return entity;
    }

    @Override
    public ComponentWrapper getWrapper() {
        return wrapper;
    }

    @Override
    public boolean testEvent(EntityEvent t) {
        if (!getEntity().equals(t.getEntity()))
            return false;
        new ArrayList<>(eventConsumers).forEach(eventConsumer -> eventConsumer.testEvent(t));
        return true;
    }

    @Override
    public WrappedEntity getWrappedEntity() {
        return this;
    }

}
