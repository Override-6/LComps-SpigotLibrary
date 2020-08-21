package fr.override.mc.lcomp.wrappers;

import org.bukkit.event.player.*;

public interface WrappedPlayerEventDispatcher {

    boolean testPlayerEvent(PlayerEvent event);

    WrappedPlayer getWrappedPlayer();
}
