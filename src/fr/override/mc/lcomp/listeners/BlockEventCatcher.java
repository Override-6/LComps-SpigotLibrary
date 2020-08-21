package fr.override.mc.lcomp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;

import java.util.function.Consumer;

public class BlockEventCatcher implements Listener {
    private final Consumer<BlockEvent> onEvent;

    public BlockEventCatcher(Consumer<BlockEvent> onEvent) {
        this.onEvent = onEvent;
    }

    @EventHandler
    private void onEvent(BlockPlaceEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(BlockBreakEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(BlockGrowEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(BlockIgniteEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(BlockPistonExtendEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(BlockPistonRetractEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(BlockBurnEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(BlockDamageEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(BlockExpEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(BlockFadeEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(BlockCanBuildEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(BlockRedstoneEvent event) {
        onEvent.accept(event);
    }

}
