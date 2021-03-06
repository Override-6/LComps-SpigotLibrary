# LComps-SpigotLibrary
A simple library to add event listeners on Entities, Players, Items or Blocks

# How to use
## The class ComponentWrapper
A ComponentWrapper instance can only be created by his builder.   
Once instancied, it can wrap any component.  
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
example of use :  

```java
ComponentWrapper.builder(plugin)
                .setWrapperFactory(new SimpleWrapperFactory())
                .withBlocksListener(new SimpleBlocksListener(plugin))
                .withEntitiesListener(new SimpleEntitiesListener(plugin))
                .withItemsListener(new SimpleItemsListener())
                .withPlayersListener(new SimplePlayersListener(plugin))
                .build();
```

An instance with default factory and ListenerHandlers can be used with `DefaultComponentWrapper.createNew`  
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
wplayer.onPlayerEvent(PlayerCommandPreProssesEvent.class, event => if (event.getCommand().contains("kick")) event.setCancelled(), "kick");
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

## ListenerHandlers interfaces
There is one Listener Interace per wrapped type 
   * PlayerListenerHandler
   * ItemsListenerHandler
   * EntitiesListenerHandler
   * BlocksListenerHandler
   
Only one ListenerHandler instance per ComponentWrapper is needed.  
ListenerHandlers handle the cache of wrapped components, and dispatch the events to each registred wrapped component  
They can also chose what kind of event will be listened or not.

# How to extend
## [WrapperFactory](https://github.com/Override-6/LComps-SpigotLibrary/blob/master/src/fr/override/mc/lcomp/WrapperFactory.java) interface
The WrapperFactory is only used by the ComponentWrapper, and help him instancing wrappers for any given component, if it was not found into the cache.  
Factories decide what kind of implementation will be used for wrappers
To inject a WrapperFactory into a ComponentWrapper, you can use his Builder.  

## ListenerHandlers interfaces
We already seen ListenerHandlers interfaces above, but their implementation can be specified by the builder of ComponentWrapper
