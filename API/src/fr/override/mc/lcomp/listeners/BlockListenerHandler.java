package fr.override.mc.lcomp.listeners;

import fr.override.mc.lcomp.wrappers.WrappedBlock;
import fr.override.mc.lcomp.wrappers.WrappedBlockEventDispatcher;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;

import java.util.Optional;

public interface BlockListenerHandler extends Listener {

    Optional<WrappedBlock> get(Block block);

    void putIfAbsent(WrappedBlockEventDispatcher dispatcher);

}
