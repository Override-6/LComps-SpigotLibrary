package fr.override.mc.lcomp;

import fr.override.mc.lcomp.listeners.BlockListenerHandler;
import fr.override.mc.lcomp.listeners.EntityListenerHandler;
import fr.override.mc.lcomp.listeners.ItemListenerHandler;
import fr.override.mc.lcomp.listeners.PlayerListenerHandler;
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
    private final BlockListenerHandler blockListenerHandler;

    @Nullable
    private final EntityListenerHandler entityListenerHandler;

    @Nullable
    private final ItemListenerHandler itemListenerHandler;

    @Nullable
    private final PlayerListenerHandler playersListener;

    private ComponentWrapper(@Nullable BlockListenerHandler blockListenerHandler,
                             @Nullable EntityListenerHandler entityListenerHandler,
                             @Nullable ItemListenerHandler itemListenerHandler,
                             @Nullable PlayerListenerHandler playersListener,
                             @NotNull WrapperFactory factory,
                             @NotNull Plugin plugin) {
        this.blockListenerHandler = blockListenerHandler;
        this.entityListenerHandler = entityListenerHandler;
        this.itemListenerHandler = itemListenerHandler;
        this.playersListener = playersListener;
        this.factory = factory;

        PluginManager manager = plugin.getServer().getPluginManager();
        if (blockListenerHandler != null)
            manager.registerEvents(blockListenerHandler, plugin);
        if (entityListenerHandler != null)
            manager.registerEvents(entityListenerHandler, plugin);
        if (itemListenerHandler != null)
            manager.registerEvents(itemListenerHandler, plugin);
        if (playersListener != null)
            manager.registerEvents(playersListener, plugin);

    }

    private final WrapperFactory factory;

    /**
     * @param block the component to wrap
     * @return the wrapper of the component
     */
    public WrappedBlock wrap(Block block) {
        if (blockListenerHandler == null)
            throw new NullPointerException("A Block Listener must be specified first.");
        return blockListenerHandler.get(block)
                .orElseGet(() -> {
                    BlockListener wrappedBlock = factory.wrap(block, this);
                    blockListenerHandler.putIfAbsent(wrappedBlock);
                    return wrappedBlock;
                });
    }

    /**
     * @param entity the component to wrap
     * @return the wrapper of the component
     */
    public WrappedEntity wrap(Entity entity) {
        if (entityListenerHandler == null)
            throw new NullPointerException("A Entity Listener must be specified first.");
        return entityListenerHandler.get(entity)
                .orElseGet(() -> {
                    EntityListener wrappedEntity = factory.wrap(entity, this);
                    entityListenerHandler.putIfAbsent(entity.getUniqueId(), wrappedEntity);
                    return wrappedEntity;
                });
    }

    /**
     * @param itemStack the component to wrap
     * @return the wrapper of the component
     */
    public WrappedItem wrap(ItemStack itemStack) {
        if (itemListenerHandler == null)
            throw new NullPointerException("A Item Listener must be specified first.");
        return itemListenerHandler.get(itemStack)
                .orElseGet(() -> {
                    ItemListener wrappedItem = factory.wrap(itemStack, this);
                    itemListenerHandler.putIfAbsent(wrappedItem);
                    return wrappedItem;
                });
    }

    /**
     * @param player the component to wrap
     * @return the wrapper of the component
     */
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

    /**
     * All methods prefixed by 'with' are optional. only the factory is needed
     */
    public static class Builder {
        @Nullable
        private BlockListenerHandler blockListenerHandler;
        @Nullable
        private EntityListenerHandler entityListenerHandler;
        @Nullable
        private ItemListenerHandler itemListenerHandler;
        @Nullable
        private PlayerListenerHandler playersListener;

        private WrapperFactory factory;
        private final Plugin plugin;

        public Builder(@NotNull Plugin plugin) {
            this.plugin = plugin;
        }

        public Builder withBlocksHandler(BlockListenerHandler listener) {
            this.blockListenerHandler = listener;
            return this;
        }

        public Builder withItemsHandler(ItemListenerHandler listener) {
            this.itemListenerHandler = listener;
            return this;
        }

        public Builder withEntitiesHandler(EntityListenerHandler listener) {
            this.entityListenerHandler = listener;
            return this;
        }

        public Builder withPlayersHandler(PlayerListenerHandler listener) {
            this.playersListener = listener;
            return this;
        }

        public Builder setWrapperFactory(WrapperFactory factory) {
            this.factory = factory;
            return this;
        }

        /**
         * @return the built ComponentWrapper instance
         * @throws NullPointerException if factory or plugin is null
         */
        public ComponentWrapper build() {
            if (factory == null)
                throw new NullPointerException("please, specify a factory instance");
            return new ComponentWrapper(
                    blockListenerHandler,
                    entityListenerHandler,
                    itemListenerHandler,
                    playersListener,
                    factory,
                    plugin
            );
        }

    }
}
