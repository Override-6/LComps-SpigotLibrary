package fr.override.mc.lcomp.wrappers;

import org.bukkit.event.Event;

import java.util.function.Consumer;

public class EventConsumer<T extends Event> {

    private final Class<T> eventClass;
    private final Consumer<T> consumer;
    private final String id;

    public EventConsumer(Class<T> eventClass, Consumer<T> consumer, String id) {
        this.eventClass = eventClass;
        this.consumer = consumer;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean testEvent(Event event) {
        if (event.getClass() == eventClass) {
            consumer.accept(eventClass.cast(event));
            return true;
        }
        return false;
    }

}
