package fr.override.mc.lcomp.listeners;

import fr.override.mc.lcomp.wrappers.WrappedItem;
import fr.override.mc.lcomp.wrappers.WrappedItemEventDispatcher;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public interface ItemListenerHandler extends Listener {

    Optional<WrappedItem> get(ItemStack itemStack);

    void putIfAbsent(WrappedItemEventDispatcher dispatcher);

}
