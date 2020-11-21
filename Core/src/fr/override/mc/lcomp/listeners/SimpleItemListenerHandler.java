package fr.override.mc.lcomp.listeners;

import fr.override.mc.lcomp.wrappers.WrappedItem;
import fr.override.mc.lcomp.wrappers.WrappedItemEventDispatcher;
import org.bukkit.event.EventHandler;
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

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class SimpleItemListenerHandler implements ItemListenerHandler {

    private final Set<WrappedItemEventDispatcher> dispatchers;

    public SimpleItemListenerHandler() {
        this.dispatchers = new HashSet<>();
    }

    @EventHandler
    private void onClick(InventoryClickEvent event) {
        checkUntilFound(dispatcher -> dispatcher.testOnClick(event));
    }

    @EventHandler
    private void onMove(InventoryMoveItemEvent event) {
        checkUntilFound(dispatcher -> dispatcher.testOnMoved(event));
    }

    @EventHandler
    private void onDrop(PlayerDropItemEvent event) {
        checkUntilFound(dispatcher -> dispatcher.testOnDrop(event));
    }

    @EventHandler
    private void onInteract(EntityInteractEvent event) {
        checkUntilFound(dispatcher -> dispatcher.testOnEntityUse(event));
    }

    @EventHandler
    private void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
        checkUntilFound(dispatcher -> dispatcher.testOnPlayerUseOnEntity(event));
    }

    @EventHandler
    private void onPlayerInteract(PlayerInteractEvent event) {
        checkUntilFound(dispatcher -> dispatcher.testOnPlayerUse(event),
                dispatcher -> dispatcher.testOnPlayerUseOnBlock(event));
    }

    @EventHandler
    private void onBlockPlace(BlockPlaceEvent event) {
        checkUntilFound(dispatcher -> dispatcher.testOnPlace(event));
    }

    @EventHandler
    private void onBlockBreak(BlockBreakEvent event) {
        checkUntilFound(dispatcher -> dispatcher.testOnPlayerBreakBlock(event));
    }

    @EventHandler
    private void onDamageByEntity(EntityDamageByEntityEvent event) {
        checkUntilFound(dispatcher -> dispatcher.testOnPlayerDamageEntity(event));
    }

    @EventHandler
    private void onEntityDeath(EntityDeathEvent event) {
        checkUntilFound(dispatcher -> dispatcher.testOnPlayerKillEntity(event));
    }

    @SafeVarargs
    private final void checkUntilFound(Predicate<WrappedItemEventDispatcher>... checks) {
        for (WrappedItemEventDispatcher dispatcher : dispatchers) {
            for (Predicate<WrappedItemEventDispatcher> check : checks) {
                if (check.test(dispatcher))
                    return;
            }
        }
    }

    private void checkUntilFound(Predicate<WrappedItemEventDispatcher> check) {
        for (WrappedItemEventDispatcher dispatcher : dispatchers) {
            if (check.test(dispatcher))
                return;
        }
    }

    @Override
    public Optional<WrappedItem> get(ItemStack itemStack) {
        for (WrappedItemEventDispatcher dispatcher : dispatchers) {
            if (dispatcher.getWrappedItem().getItem().equals(itemStack))
                return Optional.ofNullable(dispatcher.getWrappedItem());
        }
        return Optional.empty();
    }

    @Override
    public void putIfAbsent(WrappedItemEventDispatcher dispatcher) {
        dispatchers.add(dispatcher);
    }
}
