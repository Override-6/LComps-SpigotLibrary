# LComps-SpigotLibrary
A simple library to add event listeners on Entities, Players, Items or Blocks

# How to use
## The class ComponentWrapper
A ComponentWrapper can be instancied with his builder.
### ComponentWrapper.Builder
BlocksListeners, ItemsListeners, EntitiesListeners and PlayersListeners are optionnal.
WrapperFactory is obligatory.
example of use 

```scala
ComponentWrapper.builder(plugin)
                .setWrapperFactory(new SimpleWrapperFactory())
                .withBlocksListener(new SimpleBlocksListener(plugin))
                .withEntitiesListener(new SimpleEntitiesListener(plugin))
                .withItemsListener(new SimpleItemsListener())
                .withPlayersListener(new SimplePlayersListener(plugin))
                .build();
```

An instance with default factory and listeners can be used with `ComponentWrapper.defaults`
this class transform entities, blocks, items and players to a wrapped object.

## The wrapper classes
You can find 4 differents wrappers. 
   * WrappedPlayer
   * WrappedEntity
   * WrappedBlock
   * WrappedItem

Wrapper classes handles a single minecraft component, and add it some methods such as "onEvent" methods, except for WrappedItem, that ask for an event class, and a Consumer<T exte ds Event> to execute when the specified Event class is executed by standard bukkit event listeners.

There is an exception for WrappedItem because there is no abstract event class that group all possibly action made with Items or itemStacks, so please take a look at [this class](https://github.com/Override-6/LComps-SpigotLibrary/blob/master/src/fr/override/mc/lcomp/wrappers/WrappedItem.java)
