# LComps-SpigotLibrary
A simple library to add event listeners on Entities, Players, Items or Blocks

# How to use 
## the class ComponentWrapper
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
