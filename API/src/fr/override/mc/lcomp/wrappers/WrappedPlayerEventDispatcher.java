package fr.override.mc.lcomp.wrappers;

import org.bukkit.event.player.PlayerEvent;

public interface WrappedPlayerEventDispatcher {

    /**
     * Dispatch the event to every registered actions
     *
     * @param event the event to dispatch
     * @return {@code true} if an action have been fired
     */
    boolean testPlayerEvent(PlayerEvent event);

    /**
     * @return the wrapped component handled by this dispatcher
     */
    WrappedPlayer getWrappedPlayer();
}
