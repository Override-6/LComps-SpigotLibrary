# LComps-SpigotLibrary
A simple library to add event listeners on Entities, Players, Items or Blocks

# How to use
## The class ComponentWrapper
A ComponentWrapper can be instancied with his builder.
Once instancied, the object can wrap any component.
example : 
```java
WrappedPlayer wrappedPlayer = componentWrapper.wrap(bukkitPlayer);
WrappedItem wrappedItem = componentWrapper.wrap(itemStack);
WrappedEntity wrappedPig = componentWrapper.wrap(pig);
WrappedBlock wrappedEndStone = componentWrapper.wrap(endStone);
```

Any wrapped component can trigger lambdas, when a specific event affect the child.

### ComponentWrapper.Builder
BlocksListeners, ItemsListeners, EntitiesListeners and PlayersListeners are optionnal.
WrapperFactory is obligatory.
example of use 

```java
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
   * WrappedEntity
   * WrappedPlayer (extends WrappedEntity)
   * WrappedBlock
   * WrappedItem

Wrapper classes handles a single minecraft component, and add it some methods such as "onEvent" methods, except for WrappedItem, that ask for an event class, and a Consumer<T extends Event> to execute when the specified Event class is executed by standard bukkit event listeners.
events can also be specified with a String identifier, and can be deleted with removeEventConsumer(String)

example of use : 

```scala
WrappedPlayer wplayer = componentWrapper.wrap(bukkitPlayer);
wplayer.onPlayerEvent(PlayerCommandPreProssesEvent.class, event => if (event.getCommand().contains("kick") event.setCancelled(), "kick");
Thread.sleep(45000);
wplayer.removeEventConsumer("kick");
```

There is an exception for WrappedItem because there is no abstract event class that group all possibly action made with Items or itemStacks, so please take a look into [WrappedItem](https://github.com/Override-6/LComps-SpigotLibrary/blob/master/src/fr/override/mc/lcomp/wrappers/WrappedItem.java)

## Wrapped Component Event Dispatchers interfaces
There is one Wrapped Component Event Dispatcher per wrapped type 
   * WrappedPlayerEventDispatcher
   * WrappedItemEventDispatcher
   * WrappedEntityEventDispatcher
   * WrappedBlockEventDispatcher
   
   Event dispatchers dispatch registred events of a wrapped component

## Components Listeners interfaces
There is one Listener Interace per wrapped type 
   * PlayersListener
   * ItemsListener
   * EntitiesListener
   * BlocksListeners
   
Only one Listener instance per ComponentWrapper is needed.
Listeners handle the cache of wrapped components, and dispatch the events to each registred wrapped component EvnetDispatcher

# How to extend
## [WrapperFactory](https://github.com/Override-6/LComps-SpigotLibrary/blob/master/src/fr/override/mc/lcomp/WrapperFactory.java) interface
The WrapperFactory is only used by the ComponentWrapper, and help him instancing wrappers for any given component, if it was not found into the cache.
to inject a WrapperFactory into a ComponentWrapper, you can use his Builder.
