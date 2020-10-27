package fr.override.mc.lcomp.wrappers;

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

/**
 * As an item does not have any generalised event type, the events have to be split
 */
public interface WrappedItemEventDispatcher {

    boolean testOnClick(InventoryClickEvent event);

    boolean testOnMoved(InventoryMoveItemEvent event);

    boolean testOnPlayerUse(PlayerInteractEvent event);

    boolean testOnEntityUse(EntityInteractEvent event);

    boolean testOnPlayerUseOnEntity(PlayerInteractAtEntityEvent event);

    boolean testOnPlayerUseOnBlock(PlayerInteractEvent event);

    boolean testOnPlayerBreakBlock(BlockBreakEvent event);

    boolean testOnPlayerDamageEntity(EntityDamageByEntityEvent event);

    boolean testOnPlayerKillEntity(EntityDeathEvent event);

    boolean testOnDrop(PlayerDropItemEvent event);

    boolean testOnPlace(BlockPlaceEvent event);

    /**
     * @return the wrapped component handled by this dispatcher
     */
    WrappedItem getWrappedItem();

}
