package fr.override.mc.lcomp.listeners;

import fr.override.mc.lcomp.wrappers.WrappedPlayer;
import fr.override.mc.lcomp.wrappers.WrappedPlayerEventDispatcher;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.Optional;
import java.util.UUID;

public interface PlayerListenerHandler extends Listener {

    Optional<WrappedPlayer> get(Player player);
    Optional<WrappedPlayer> get(UUID uuid);

    void putIfAbsent(UUID id, WrappedPlayerEventDispatcher dispatcher);

}
