package fr.override.mc.lcomp.wrappers;

import org.bukkit.event.block.BlockEvent;

public interface WrappedBlockEventDispatcher {

    boolean testBlockEvent(BlockEvent event);

    WrappedBlock getWrappedBlock();

}
