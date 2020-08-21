package fr.override.mc.lcomp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.spigotmc.event.entity.EntityDismountEvent;
import org.spigotmc.event.entity.EntityMountEvent;

import java.util.function.Consumer;

public class EntityEventCatcher implements Listener {

    private final Consumer<EntityEvent> entityEventConsumer;

    public EntityEventCatcher(Consumer<EntityEvent> entityEventConsumer) {
        this.entityEventConsumer = entityEventConsumer;
    }

    @EventHandler
    private void onEvent(EntityInteractEvent event) {
        entityEventConsumer.accept(event);
    }

    @EventHandler
    private void onEvent(EntityDeathEvent event) {
        entityEventConsumer.accept(event);
    }

    @EventHandler
    private void onEvent(EntityDamageEvent event) {
        entityEventConsumer.accept(event);
    }

    @EventHandler
    private void onEvent(EntityPortalEnterEvent event) {
        entityEventConsumer.accept(event);
    }

    @EventHandler
    private void onEvent(EntityBreakDoorEvent event) {
        entityEventConsumer.accept(event);
    }

    @EventHandler
    private void onEvent(EntityChangeBlockEvent event) {
        entityEventConsumer.accept(event);
    }

    @EventHandler
    private void onEvent(EntityCombustEvent event) {
        entityEventConsumer.accept(event);
    }

    @EventHandler
    private void onEvent(EntityRegainHealthEvent event) {
        entityEventConsumer.accept(event);
    }

    @EventHandler
    private void onEvent(EntityTameEvent event) {
        entityEventConsumer.accept(event);
    }

    @EventHandler
    private void onEvent(EntityTargetEvent event) {
        entityEventConsumer.accept(event);
    }

    @EventHandler
    private void onEvent(EntityExplodeEvent event) {
        entityEventConsumer.accept(event);
    }

    @EventHandler
    private void onEvent(EntityMountEvent event) {
        entityEventConsumer.accept(event);
    }

    @EventHandler
    private void onEvent(EntityDismountEvent event) {
        entityEventConsumer.accept(event);
    }

    @EventHandler
    private void onEvent(EntityUnleashEvent event) {
        entityEventConsumer.accept(event);
    }

    @EventHandler
    private void onEvent(EntityTargetLivingEntityEvent event) {
        entityEventConsumer.accept(event);
    }

    @EventHandler
    private void onEvent(EntityPortalEvent event) {
        entityEventConsumer.accept(event);
    }

    @EventHandler
    private void onEvent(EntityShootBowEvent event) {
        entityEventConsumer.accept(event);
    }

}
