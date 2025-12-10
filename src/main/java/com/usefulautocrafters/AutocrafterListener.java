package com.usefulautocrafters;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Crafter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.CrafterCraftEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AutocrafterListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAutocrafterCraft(CrafterCraftEvent event) {
        Block block = event.getBlock();
        
        // Ensure we're dealing with a Crafter block
        if (!(block.getState() instanceof Crafter crafter)) {
            return;
        }

        Inventory inventory = crafter.getInventory();
        
        // Check all slots in the crafter (0-8 are crafting slots)
        for (int slot = 0; slot < 9; slot++) {
            // Check if this slot is enabled (not disabled in the crafter UI)
            if (!crafter.isSlotDisabled(slot)) {
                ItemStack item = inventory.getItem(slot);
                
                // If the slot has an item and only 1 of it, cancel the crafting
                if (item != null && item.getType() != Material.AIR && item.getAmount() == 1) {
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }
}
