package fr.override.mc.lcomp;

import fr.override.mc.lcomp.wrappers.BlockListener;
import fr.override.mc.lcomp.wrappers.EntityListener;
import fr.override.mc.lcomp.wrappers.ItemListener;
import fr.override.mc.lcomp.wrappers.PlayerListener;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface  WrapperFactory {

    ItemListener wrap(ItemStack itemStack, ComponentWrapper wrapper);

    BlockListener wrap(Block block, ComponentWrapper wrapper);

    EntityListener wrap(Entity entity, ComponentWrapper wrapper);

    PlayerListener wrap(Player player, ComponentWrapper wrapper);

}
