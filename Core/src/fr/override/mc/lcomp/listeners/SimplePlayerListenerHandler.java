package fr.override.mc.lcomp.listeners;

import fr.override.mc.lcomp.wrappers.WrappedPlayer;
import fr.override.mc.lcomp.wrappers.WrappedPlayerEventDispatcher;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class SimplePlayerListenerHandler implements PlayerListenerHandler {

    private final Map<UUID, WrappedPlayerEventDispatcher> players;

    public SimplePlayerListenerHandler(Plugin plugin) {
        this.players = new HashMap<>();
        new PlayerEventCatcher(this::onEvent, plugin);
    }

    private void onEvent(PlayerEvent event) {
        findDispatcher(event).ifPresent(dispatcher -> dispatcher.testPlayerEvent(event));
    }

    @Override
    public Optional<WrappedPlayer> get(Player player) {
        return get(player.getUniqueId());
    }

    @Override
    public Optional<WrappedPlayer> get(UUID uuid) {
        WrappedPlayerEventDispatcher dispatcher = players.get(uuid);
        if (dispatcher == null)
            return Optional.empty();
        return Optional.ofNullable(dispatcher.getWrappedPlayer());
    }

    @Override
    public void putIfAbsent(UUID id, WrappedPlayerEventDispatcher dispatcher) {
        players.putIfAbsent(id, dispatcher);
    }

    private Optional<WrappedPlayerEventDispatcher> findDispatcher(PlayerEvent event) {
        return Optional.ofNullable(players.get(event.getPlayer().getUniqueId()));
    }


}
