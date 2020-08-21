package fr.override.mc.lcomp;

import fr.override.mc.lcomp.listeners.*;
import fr.override.mc.lcomp.wrappers.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ComponentWrapper {

    @Nullable
    private final BlocksListener blocksListener;

    @Nullable
    private final EntitiesListener entitiesListener;

    @Nullable
    private final ItemsListener itemsListener;

    @Nullable
    private final PlayersListener playersListener;

    public ComponentWrapper(@Nullable BlocksListener blocksListener,
                            @Nullable EntitiesListener entitiesListener,
                            @Nullable ItemsListener itemsListener,
                            @Nullable PlayersListener playersListener,
                            @NotNull WrapperFactory factory,
                            @NotNull Plugin plugin) {
        this.blocksListener = blocksListener;
        this.entitiesListener = entitiesListener;
        this.itemsListener = itemsListener;
        this.playersListener = playersListener;
        this.factory = factory;

        PluginManager manager = plugin.getServer().getPluginManager();
        if (blocksListener != null)
            manager.registerEvents(blocksListener, plugin);
        if (entitiesListener != null)
            manager.registerEvents(entitiesListener, plugin);
        if (itemsListener != null)
            manager.registerEvents(itemsListener, plugin);
        if (playersListener != null)
            manager.registerEvents(playersListener, plugin);

    }

    private final WrapperFactory factory;

    public WrappedBlock wrap(Block block) {
        if (blocksListener == null)
            throw new NullPointerException("A Block Listener must be specified first.");
        return blocksListener.get(block)
                .orElseGet(() -> {
                    BlockListener wrappedBlock = factory.wrap(block, this);
                    blocksListener.putIfAbsent(wrappedBlock);
                    return wrappedBlock;
                });
    }

    public WrappedEntity wrap(Entity entity) {
        if (entitiesListener == null)
            throw new NullPointerException("A Entity Listener must be specified first.");
        return entitiesListener.get(entity)
                .orElseGet(() -> {
                    EntityListener wrappedEntity = factory.wrap(entity, this);
                    entitiesListener.putIfAbsent(entity.getUniqueId(), wrappedEntity);
                    return wrappedEntity;
                });
    }

    public WrappedItem wrap(ItemStack itemStack) {
        if (itemsListener == null)
            throw new NullPointerException("A Item Listener must be specified first.");
        return itemsListener.get(itemStack)
                .orElseGet(() -> {
                    ItemListener wrappedItem = factory.wrap(itemStack, this);
                    itemsListener.putIfAbsent(wrappedItem);
                    return wrappedItem;
                });
    }

    public WrappedPlayer wrap(Player player) {
        if (playersListener == null)
            throw new NullPointerException("A Player Listener must be specified first.");
        return playersListener.get(player)
                .orElseGet(() -> {
                    PlayerListener wrappedPlayer = factory.wrap(player, this);
                    playersListener.putIfAbsent(player.getUniqueId(), wrappedPlayer);
                    return wrappedPlayer;
                });
    }

    public static Builder builder(@NotNull Plugin plugin) {
        return new Builder(plugin);
    }

    public static ComponentWrapper defaults(Plugin plugin) {
        return builder(plugin)
                .setWrapperFactory(new SimpleWrapperFactory())
                .withBlocksListener(new SimpleBlocksListener(plugin))
                .withEntitiesListener(new SimpleEntitiesListener(plugin))
                .withItemsListener(new SimpleItemsListener())
                .withPlayersListener(new SimplePlayersListener(plugin))
                .build();
    }


    private static class Builder {
        @Nullable
        private BlocksListener blocksListener;
        @Nullable
        private EntitiesListener entitiesListener;
        @Nullable
        private ItemsListener itemsListener;
        @Nullable
        private PlayersListener playersListener;

        private WrapperFactory factory;
        private final Plugin plugin;

        public Builder(@NotNull Plugin plugin) {
            this.plugin = plugin;
        }

        public Builder withBlocksListener(BlocksListener listener) {
            this.blocksListener = listener;
            return this;
        }

        public Builder withItemsListener(ItemsListener listener) {
            this.itemsListener = listener;
            return this;
        }

        public Builder withEntitiesListener(EntitiesListener listener) {
            this.entitiesListener = listener;
            return this;
        }

        public Builder withPlayersListener(PlayersListener listener) {
            this.playersListener = listener;
            return this;
        }

        public Builder setWrapperFactory(WrapperFactory factory) {
            this.factory = factory;
            return this;
        }

        public ComponentWrapper build() {
            return new ComponentWrapper(
                    blocksListener, entitiesListener, itemsListener, playersListener, factory, plugin
            );
        }

    }
}
