package fr.override.mc.lcomp.wrappers;

import fr.override.mc.lcomp.ComponentWrapper;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class SimplePlayerListener extends SimpleEntityListener implements PlayerListener {

    private final UUID playerUUID;
    private final List<EventConsumer<? extends PlayerEvent>> eventConsumers;
    private Player player = null;

    public SimplePlayerListener(Player player, ComponentWrapper wrapper) {
        super(player, wrapper);
        this.playerUUID = player.getUniqueId();
        this.eventConsumers = new ArrayList<>();
    }

    @Override
    public <T extends PlayerEvent> void onPlayerEvent(Class<T> classOfEvent, Consumer<T> eventConsumer) {
        onPlayerEvent(classOfEvent, eventConsumer, "");
    }

    @Override
    public <T extends PlayerEvent> void onPlayerEvent(Class<T> classOfEvent, Consumer<T> eventConsumer, String id) {
        eventConsumers.add(new EventConsumer<>(classOfEvent, eventConsumer, id));
    }

    @Override
    public void removeEventConsumers(String id) {
        eventConsumers.removeIf(eventConsumer -> eventConsumer.getId().equals(id));
    }

    @Override
    public Player getPlayer() {
        return Bukkit.getPlayer(playerUUID);
    }

    @Override
    public boolean testPlayerEvent(PlayerEvent event) {
        if (!playerUUID.equals(event.getPlayer().getUniqueId()))
            return false;
        new ArrayList<>(eventConsumers).forEach(eventConsumer -> eventConsumer.testEvent(event));
        return true;
    }

    @Override
    public WrappedPlayer getWrappedPlayer() {
        return this;
    }

}
