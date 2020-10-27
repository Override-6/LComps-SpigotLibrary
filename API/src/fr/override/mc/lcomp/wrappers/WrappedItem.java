package fr.override.mc.lcomp.wrappers;

import fr.override.mc.lcomp.ComponentWrapper;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * As an item does not have any generalised event type, the events have to be split
 */
public interface WrappedItem {

    void setOnClick(Consumer<InventoryClickEvent> eventConsumer);

    void setOnMoved(Consumer<InventoryMoveItemEvent> eventConsumer);

    void setOnPlayerUse(Consumer<PlayerInteractEvent> eventConsumer);

    void setOnEntityUse(Consumer<EntityInteractEvent> eventConsumer);

    void setOnPlayerUseOnEntity(Consumer<PlayerInteractAtEntityEvent> eventConsumer);

    void setOnPlayerUseOnBlock(Consumer<PlayerInteractEvent> eventConsumer);

    void setOnPlayerBreakBlock(Consumer<BlockBreakEvent> eventConsumer);

    void setOnPlayerDamageEntity(Consumer<EntityDamageByEntityEvent> eventConsumer);

    void setOnPlayerKillEntity(Consumer<EntityDeathEvent> eventConsumer);

    void setOnDrop(Consumer<PlayerDropItemEvent> eventConsumer);

    void setOnPlace(Consumer<BlockPlaceEvent> eventConsumer);

    /**
     * @return true if this item is a block material
     */
    default boolean isBlock() {
        return getItem().getType().isBlock();
    }

    /**
     * @return the bukkit component
     */
    ItemStack getItem();

    /**
     * @return the attached wrapped block or {@code null} if it wasn't
     */
    @Nullable
    WrappedBlock getAttachedBlock();

    /**
     * set to the target this WrappedItem instance to spawn when pop method is called
     *
     * @param block the target
     */
    void attachTo(WrappedBlock block);

    /**
     * @return the {@link ComponentWrapper} that handles this wrapper
     */
    ComponentWrapper getWrapper();
}
