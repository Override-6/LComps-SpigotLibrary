package fr.override.mc.lcomp;

import fr.override.mc.lcomp.wrappers.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SimpleWrapperFactory implements WrapperFactory {


    @Override
    public ItemListener wrap(ItemStack itemStack, ComponentWrapper wrapper) {
        return new SimpleItemListener(itemStack, wrapper);
    }

    @Override
    public BlockListener wrap(Block block, ComponentWrapper wrapper) {
        return new SimpleBlockListener(block, wrapper);
    }

    @Override
    public EntityListener wrap(Entity entity, ComponentWrapper wrapper) {
        return new SimpleEntityListener(entity, wrapper);
    }

    @Override
    public PlayerListener wrap(Player player, ComponentWrapper wrapper) {
        return new SimplePlayerListener(player, wrapper);
    }
}
