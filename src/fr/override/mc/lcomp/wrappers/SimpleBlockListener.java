package fr.override.mc.lcomp.wrappers;

import fr.override.mc.lcomp.ComponentWrapper;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SimpleBlockListener implements BlockListener {

    private final Block block;
    private final List<EventConsumer<? extends BlockEvent>> eventConsumers;
    private final ComponentWrapper wrapper;
    private WrappedItem dropItem;

    public SimpleBlockListener(@NotNull Block block, ComponentWrapper wrapper) {
        this.block = block;
        this.eventConsumers = new ArrayList<>();
        this.wrapper = wrapper;
    }

    @Override
    public <T extends BlockEvent> void onBlockEvent(Class<T> eventClass, Consumer<T> consumer) {
        onBlockEvent(eventClass, consumer, "");
    }

    @Override
    public <T extends BlockEvent> void onBlockEvent(Class<T> eventClass, Consumer<T> consumer, String id) {
        eventConsumers.add(new EventConsumer<>(eventClass, consumer, id));
    }

    @Override
    public void removeEventConsumers(String id) {
        eventConsumers.removeIf(eventConsumer -> eventConsumer.getId().equals(id));
    }

    @Override
    public WrappedItem pop() {
        block.breakNaturally(dropItem.getItem());
        return dropItem;
    }

    @Override
    public Block getBlock() {
        return block;
    }

    @Override
    public void setDropItem(WrappedItem item) {
        this.dropItem = item;
    }

    @Override
    public WrappedItem getDropItem() {
        return dropItem;
    }

    @Override
    public boolean testBlockEvent(BlockEvent event) {
        if (!getBlock().equals(event.getBlock()))
            return false;
        new ArrayList<>(eventConsumers).forEach(eventConsumer -> eventConsumer.testEvent(event));
        return true;
    }

    @Override
    public WrappedBlock getWrappedBlock() {
        return this;
    }

    @Override
    public ComponentWrapper getWrapper() {
        return wrapper;
    }
}
