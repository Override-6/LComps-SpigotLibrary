package fr.override.mc.lcomp;

import fr.override.mc.lcomp.wrappers.BlockListener;
import fr.override.mc.lcomp.wrappers.EntityListener;
import fr.override.mc.lcomp.wrappers.ItemListener;
import fr.override.mc.lcomp.wrappers.PlayerListener;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Factory is called to create a new listener instance when it is not registered into cache
 */
public interface  WrapperFactory {

    /**
     *
     * @param itemStack the component to wrap
     * @param wrapper the ComponentWrapper that request a new Wrapper instance
     * @return {@link ItemListener}
     */
    ItemListener wrap(ItemStack itemStack, ComponentWrapper wrapper);

    /**
     *
     * @param block the component to wrap
     * @param wrapper the ComponentWrapper that request a new Wrapper instance
     * @return {@link BlockListener}
     */
    BlockListener wrap(Block block, ComponentWrapper wrapper);

    /**
     *
     * @param entity the component to wrap
     * @param wrapper the ComponentWrapper that request a new Wrapper instance
     * @return {@link EntityListener}
     */
    EntityListener wrap(Entity entity, ComponentWrapper wrapper);

    /**
     *
     * @param player the component to wrap
     * @param wrapper the ComponentWrapper that request a new Wrapper instance
     * @return {@link PlayerListener}
     */
    PlayerListener wrap(Player player, ComponentWrapper wrapper);

}
