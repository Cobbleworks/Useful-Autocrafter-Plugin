package com.usefulautocrafters;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Crafter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.CrafterCraftEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Protects crafter ingredients from crafting and automated inventory extraction.
 */
public class AutocrafterListener implements Listener {

    private final UsefulAutocrafters plugin;
    
    public AutocrafterListener(UsefulAutocrafters plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onAutocrafterCraft(CrafterCraftEvent event) {
        if (!plugin.isFeatureEnabled()) {
            return;
        }
        
        Block block = event.getBlock();
        
        if (!(block.getState() instanceof Crafter crafter)) {
            return;
        }

        Inventory inventory = crafter.getInventory();
        
        for (int slot = 0; slot < 9; slot++) {
            if (!crafter.isSlotDisabled(slot)) {
                ItemStack item = inventory.getItem(slot);
                
                if (item != null && item.getType() != Material.AIR && item.getAmount() == 1) {
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    /**
     * Stops hoppers and other automated inventories from pulling recipe items out
     * of a crafter. Crafted results are dispensed through the crafter's output face
     * and do not use the crafter as the transfer source.
     */
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onAutomatedInventoryMove(InventoryMoveItemEvent event) {
        boolean sourceIsCrafter = event.getSource().getType() == InventoryType.CRAFTER;
        if (shouldBlockAutomatedExtraction(plugin.isFeatureEnabled(), sourceIsCrafter)) {
            event.setCancelled(true);
        }
    }

    static boolean shouldBlockAutomatedExtraction(boolean featureEnabled, boolean sourceIsCrafter) {
        return featureEnabled && sourceIsCrafter;
    }
}
