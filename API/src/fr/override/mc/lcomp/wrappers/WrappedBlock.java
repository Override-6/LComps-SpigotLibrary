package fr.override.mc.lcomp.wrappers;

import fr.override.mc.lcomp.ComponentWrapper;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockEvent;

import java.util.function.Consumer;

public interface WrappedBlock {

    /**
     * @param eventClass the kind of event to listen
     * @param consumer   the action to perform
     * @param <T>        the type of event
     */
    <T extends BlockEvent> void onBlockEvent(Class<T> eventClass, Consumer<T> consumer);

    /**
     * @param eventClass the kind of event to listen
     * @param consumer   the action to perform
     * @param id         the identifier of the event
     * @param <T>        the type of event
     */
    <T extends BlockEvent> void onBlockEvent(Class<T> eventClass, Consumer<T> consumer, String id);

    /**
     * Unregisters any event action bound to an id
     * @param id the identifier
     */
    void removeEventConsumers(String id);

    /**
     * destroy the block and returns the drop item
     * @return the dropped WrappedItem
     */
    WrappedItem pop();

    /**
     * @return the bukkit block
     */
    Block getBlock();

    /**
     * @param item wrapped item to set
     */
    void setDropItem(WrappedItem item);

    /**
     * @return the item that would be dropped by the pop method
     */
    WrappedItem getDropItem();

    /**
     * @return the {@link ComponentWrapper} that handles this wrapper
     */
    ComponentWrapper getWrapper();
}
