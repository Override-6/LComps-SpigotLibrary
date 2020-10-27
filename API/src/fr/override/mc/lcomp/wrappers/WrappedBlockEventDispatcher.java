package fr.override.mc.lcomp.wrappers;

import org.bukkit.event.block.BlockEvent;

public interface WrappedBlockEventDispatcher {

    /**
     * Dispatch the event to every registered actions
     *
     * @param event the event to dispatch
     * @return {@code true} if an action have been fired
     */
    boolean testBlockEvent(BlockEvent event);

    /**
     * @return the wrapped component handled by this dispatcher
     */
    WrappedBlock getWrappedBlock();

}
