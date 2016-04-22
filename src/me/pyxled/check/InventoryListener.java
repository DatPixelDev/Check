package me.pyxled.check;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Pyxled Development 2016 (c)
 * File created: 02/25/2016
 */
public class InventoryListener implements Listener {
    @EventHandler
    public void on(InventoryClickEvent e){
        if(e.getInventory().getName().equalsIgnoreCase("Viewing")){
            e.setCancelled(true);
        }
    }
}
