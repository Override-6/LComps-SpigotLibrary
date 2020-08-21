package fr.override.mc.lcomp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import java.util.function.Consumer;


public class PlayerEventCatcher implements Listener {
    private final Consumer<PlayerEvent> onEvent;

    public PlayerEventCatcher(Consumer<PlayerEvent> onEvent, Plugin plugin) {
        this.onEvent = onEvent;
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents(this, plugin);
    }
    /**
     * The class was fragmented because of the high
     * amount of methods cause the Bukkit EventDispatcher to do not call them.
     * */
    @EventHandler
    private void onEvent(PlayerMoveEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(AsyncPlayerChatEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerCommandPreprocessEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerDropItemEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerItemDamageEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerJoinEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerInteractEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerQuitEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerToggleSneakEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerToggleFlightEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerToggleSprintEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerAnimationEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerArmorStandManipulateEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerFishEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerKickEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerLoginEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerPortalEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerRespawnEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerVelocityEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerTeleportEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerItemBreakEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerItemConsumeEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerItemHeldEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerRegisterChannelEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerUnregisterChannelEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerShearEntityEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerLevelChangeEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerBedEnterEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerBedLeaveEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerBucketFillEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerChangedWorldEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerEditBookEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerEggThrowEvent event) {
        onEvent.accept(event);
    } //stopped here

    @EventHandler
    private void onEvent(PlayerBucketEmptyEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerExpChangeEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerGameModeChangeEvent event) {
        onEvent.accept(event);
    }

    @EventHandler
    private void onEvent(PlayerSpawnLocationEvent event) {
        onEvent.accept(event);
    }


}
