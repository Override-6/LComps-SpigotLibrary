package fr.override.mc.lcomp.listeners;

import fr.override.mc.lcomp.wrappers.WrappedBlock;
import fr.override.mc.lcomp.wrappers.WrappedBlockEventDispatcher;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class SimpleBlocksListener implements BlocksListener {

    private final Set<WrappedBlockEventDispatcher> dispatchers;

    public SimpleBlocksListener(Plugin plugin) {
        this.dispatchers = new HashSet<>();
        plugin.getServer()
                .getPluginManager()
                .registerEvents(new BlockEventCatcher(this::onEvent), plugin);
    }

    public void onEvent(BlockEvent event) {
        testUntilFound(dispatcher -> dispatcher.testBlockEvent(event));
    }

    @Override
    public Optional<WrappedBlock> get(Block block) {
        for (WrappedBlockEventDispatcher dispatcher : dispatchers) {
            if (dispatcher.getWrappedBlock().getBlock().equals(block))
                return Optional.ofNullable(dispatcher.getWrappedBlock());
        }
        return Optional.empty();
    }

    @Override
    public void putIfAbsent(WrappedBlockEventDispatcher dispatcher) {
        dispatchers.add(dispatcher);
    }

    @SafeVarargs
    private void testUntilFound(Predicate<WrappedBlockEventDispatcher>... checks) {
        for (WrappedBlockEventDispatcher dispatcher : dispatchers) {
            for (Predicate<WrappedBlockEventDispatcher> check : checks) {
                if (check.test(dispatcher))
                    return;
            }
        }
    }

}
