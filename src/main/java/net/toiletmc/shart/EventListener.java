package net.toiletmc.shart;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

public class EventListener implements Listener {
    @EventHandler
    public void onMerge(ItemMergeEvent event) {
        if (event.getEntity().hasMetadata("shart")) event.setCancelled(true);
    }

    @EventHandler
    public void onPickUp(InventoryPickupItemEvent event) {
        if (event.getItem().hasMetadata("shart")) event.setCancelled(true);
    }
}
