package fr.override.mc.lcomp;

import fr.override.mc.lcomp.listeners.SimpleBlockListenerHandler;
import fr.override.mc.lcomp.listeners.SimpleEntityListenerHandler;
import fr.override.mc.lcomp.listeners.SimpleItemListenerHandler;
import fr.override.mc.lcomp.listeners.SimplePlayerListenerHandler;
import org.bukkit.plugin.Plugin;

public class DefaultComponentWrapper {

    public static ComponentWrapper createNew(Plugin plugin) {
        return ComponentWrapper.builder(plugin)
                .setWrapperFactory(new SimpleWrapperFactory())
                .withBlocksHandler(new SimpleBlockListenerHandler(plugin))
                .withEntitiesHandler(new SimpleEntityListenerHandler(plugin))
                .withItemsHandler(new SimpleItemListenerHandler())
                .withPlayersHandler(new SimplePlayerListenerHandler(plugin))
                .build();
    }

}
