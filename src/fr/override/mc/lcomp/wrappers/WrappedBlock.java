package fr.override.mc.lcomp.wrappers;

import fr.override.mc.lcomp.ComponentWrapper;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.function.Consumer;

public interface WrappedBlock {

    <T extends BlockEvent> void onBlockEvent(Class<T> eventClass, Consumer<T> consumer);

    <T extends BlockEvent> void onBlockEvent(Class<T> eventClass, Consumer<T> consumer, String id);

    void removeEventConsumers(String id);

    WrappedItem pop();

    Block getBlock();

    void setDropItem(WrappedItem item);

    WrappedItem getDropItem();

    ComponentWrapper getWrapper();
}
