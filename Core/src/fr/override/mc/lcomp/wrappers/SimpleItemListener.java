package fr.override.mc.lcomp.wrappers;

import fr.override.mc.lcomp.ComponentWrapper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class SimpleItemListener implements ItemListener {

    private final ItemStack item;
    private final Consumers consumers;
    private final ComponentWrapper wrapper;
    private WrappedBlock attachedBlock;

    public SimpleItemListener(ItemStack item, ComponentWrapper wrapper) {
        this.item = item;
        this.consumers = new Consumers();
        this.wrapper = wrapper;
    }

    @Override
    public void setOnClick(Consumer<InventoryClickEvent> eventConsumer) {
        consumers.onClick = eventConsumer;
    }

    @Override
    public void setOnMoved(Consumer<InventoryMoveItemEvent> eventConsumer) {
        consumers.onMoved = eventConsumer;
    }

    @Override
    public void setOnPlayerUse(Consumer<PlayerInteractEvent> eventConsumer) {
        consumers.onPlayerUse = eventConsumer;
    }

    @Override
    public void setOnEntityUse(Consumer<EntityInteractEvent> eventConsumer) {
        consumers.onEntityUse = eventConsumer;
    }

    @Override
    public void setOnPlayerUseOnEntity(Consumer<PlayerInteractAtEntityEvent> eventConsumer) {
        consumers.onPlayerUseOnEntity = eventConsumer;
    }

    @Override
    public void setOnPlayerUseOnBlock(Consumer<PlayerInteractEvent> eventConsumer) {
        consumers.onPlayerUseOnBlock = eventConsumer;
    }

    @Override
    public void setOnPlayerBreakBlock(Consumer<BlockBreakEvent> eventConsumer) {
        consumers.onPlayerBreakBlock = eventConsumer;
    }


    @Override
    public void setOnPlayerDamageEntity(Consumer<EntityDamageByEntityEvent> eventConsumer) {
        consumers.onPlayerDamageEntity = eventConsumer;
    }

    @Override
    public void setOnPlayerKillEntity(Consumer<EntityDeathEvent> eventConsumer) {
        consumers.onPlayerKillEntity = eventConsumer;
    }

    @Override
    public void setOnDrop(Consumer<PlayerDropItemEvent> eventConsumer) {
        consumers.onDrop = eventConsumer;
    }

    @Override
    public void setOnPlace(Consumer<BlockPlaceEvent> eventConsumer) {
        consumers.onPlace = eventConsumer;
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public @Nullable WrappedBlock getAttachedBlock() {
        return attachedBlock;
    }

    @Override
    public void attachTo(WrappedBlock block) {
        block.setDropItem(this);
        this.attachedBlock = block;
    }

    @Override
    public boolean testOnClick(InventoryClickEvent event) {
        if (consumers.onClick == null)
            return false;
        if (getItem().equals(event.getCurrentItem())) {
            consumers.onClick.accept(event);
            return true;
        }
        return false;
    }

    @Override
    public boolean testOnMoved(InventoryMoveItemEvent event) {
        if (consumers.onMoved == null)
            return false;
        if (getItem().equals(event.getItem())) {
            consumers.onMoved.accept(event);
            return true;
        }
        return false;
    }

    @Override
    public boolean testOnPlayerUse(PlayerInteractEvent event) {
        if (consumers.onPlayerUse == null)
            return false;
        if (getItem().equals(event.getItem())) {
            consumers.onPlayerUse.accept(event);
            return true;
        }
        return false;
    }

    @Override
    public boolean testOnEntityUse(EntityInteractEvent event) {
        if (consumers.onEntityUse == null)
            return false;
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity living) {
            EntityEquipment equipment = living.getEquipment();
            if (equipment == null)
                return false;
            ItemStack main = equipment.getItemInHand();
            if (getItem().equals(main)) {
                consumers.onEntityUse.accept(event);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean testOnPlayerUseOnEntity(PlayerInteractAtEntityEvent event) {
        EntityEquipment equipment = event.getPlayer().getEquipment();
        if (consumers.onPlayerUseOnEntity == null || equipment == null)
            return false;
        ItemStack main = equipment.getItemInHand();
        if (getItem().equals(main)) {
            consumers.onPlayerUseOnEntity.accept(event);
            return true;
        }
        return false;
    }

    @Override
    public boolean testOnPlayerUseOnBlock(PlayerInteractEvent event) {
        if (consumers.onPlayerUseOnBlock == null)
            return false;
        if (event.getClickedBlock() != null && getItem().equals(event.getItem())) {
            consumers.onPlayerUseOnBlock.accept(event);
            return true;
        }
        return false;
    }

    @Override
    public boolean testOnPlayerBreakBlock(BlockBreakEvent event) {
        EntityEquipment equipment = event.getPlayer().getEquipment();
        if (consumers.onPlayerBreakBlock == null || equipment == null)
            return false;
        if (getItem().equals(equipment.getItemInHand())) {
            consumers.onPlayerBreakBlock.accept(event);
            return true;
        }
        return false;
    }

    @Override
    public boolean testOnPlayerDamageEntity(EntityDamageByEntityEvent event) {
        if (consumers.onPlayerDamageEntity == null)
            return false;
        if (!(event.getDamager() instanceof Player player))
            return false;
        EntityEquipment equipment = player.getEquipment();
        if (equipment == null || getItem().equals(equipment.getItemInHand()))
            return false;
        consumers.onPlayerDamageEntity.accept(event);
        return true;
    }

    @Override
    public boolean testOnPlayerKillEntity(EntityDeathEvent event) {
        EntityDamageEvent cause = event.getEntity().getLastDamageCause();
        if (consumers.onPlayerKillEntity == null || cause == null)
            return false;

        if (!(cause.getEntity() instanceof Player player))
            return false;
        EntityEquipment equipment = player.getEquipment();
        if (equipment == null || getItem().equals(equipment.getItemInHand()))
            return false;
        consumers.onPlayerKillEntity.accept(event);
        return true;
    }

    @Override
    public boolean testOnDrop(PlayerDropItemEvent event) {
        if (consumers.onDrop == null)
            return false;
        if (getItem().equals(event.getItemDrop().getItemStack())) {
            consumers.onDrop.accept(event);
            return true;
        }
        return false;
    }

    @Override
    public boolean testOnPlace(BlockPlaceEvent event) {
        if (!isBlock() || consumers.onPlace == null)
            return false;
        if (getItem().equals(event.getItemInHand())) {
            consumers.onPlace.accept(event);
            return true;
        }
        return false;
    }

    @Override
    public WrappedItem getWrappedItem() {
        return this;
    }

    @Override
    public ComponentWrapper getWrapper() {
        return wrapper;
    }

    private static class Consumers {
        public Consumer<BlockBreakEvent> onPlayerBreakBlock;
        private Consumer<PlayerInteractEvent> onPlayerUseOnBlock;
        public Consumer<EntityDamageByEntityEvent> onPlayerDamageEntity;
        public Consumer<EntityDeathEvent> onPlayerKillEntity;
        private Consumer<InventoryClickEvent> onClick;
        private Consumer<InventoryMoveItemEvent> onMoved;
        private Consumer<PlayerInteractEvent> onPlayerUse;
        private Consumer<EntityInteractEvent> onEntityUse;
        private Consumer<PlayerInteractAtEntityEvent> onPlayerUseOnEntity;
        private Consumer<PlayerDropItemEvent> onDrop;
        private Consumer<BlockPlaceEvent> onPlace;
    }

}
