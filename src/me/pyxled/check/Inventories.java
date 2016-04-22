package me.pyxled.check;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Pyxled Development 2016 (c)
 * File created: 02/24/2016
 */
public class Inventories {
    @SuppressWarnings("deprecation")
    public static void checkTarget(final Player target, final Player viewer){
        final Inventory inventory = Bukkit.createInventory(null, 54, "Viewing");
        Core.getInstance().getServer().getScheduler().scheduleAsyncRepeatingTask(Core.getInstance(), new Runnable() {
            @Override
            public void run() {
                addStack(Material.SIGN, 1, (short)0, "&c&lInformation", inventory, 0, "&7Name: &e" + target.getName(), "&7Health: &e" + target.getHealth() + "&7/&e" + target.getMaxHealth(), "&7Food Level: &e" + target.getFoodLevel() + "&7/&e20", "&7Gamemode: &e" + target.getGameMode().toString().toUpperCase(), "&7EXP: &e" + target.getLevel() + "(" + target.getTotalExperience() + ")");
                addPane(1, inventory);
                addPane(2, inventory);
                addSkullItem(target, inventory, 3, "&7Rank: &eUnavailable in this Version", "&7OP: " + isOP(target));
                addPane(4, inventory);
                addPlayerHelm(target, inventory, 5);
                addPlayerChestplate(target, inventory, 6);
                addPlayerLeggings(target, inventory, 7);
                addPlayerBoots(target, inventory, 8);
                //
                addPane(9, inventory);
                addPane(10, inventory);
                addPane(11, inventory);
                addPane(12, inventory);
                addPane(13, inventory);
                addPane(14, inventory);
                addPane(15, inventory);
                addPane(16, inventory);
                addPane(17, inventory);
                //
                addPlayerInventory(target, inventory);
            }
        }, 0, 10);
        viewer.openInventory(inventory);
    }
    public static void addSkullItem(Player owner, Inventory inv, int slot, String... lore){
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta meta = (SkullMeta)skull.getItemMeta();
        meta.setOwner(owner.getName());
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&l" + owner.getName()));
        List<String> newlore = new ArrayList<>();
        for(String str : lore){
            newlore.add(ChatColor.translateAlternateColorCodes('&', str));
        }
        meta.setLore(newlore);
        skull.setItemMeta(meta);
        inv.setItem(slot, skull);
    }
    public static void addPlayerInventory(Player p, Inventory inv){
        int num = 18;
        for(ItemStack i : p.getInventory().getContents()){
            inv.setItem(num, i);
            num++;
        }
    }
    public static void addPane(int slot, Inventory inv){
        ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)7);
        inv.setItem(slot, itemStack);
    }
    public static void addPlayerHelm(Player p, Inventory inv, int slot){
        ItemStack helm;
        if(p.getInventory().getHelmet() == null || p.getInventory().getHelmet().getType() == Material.AIR){
            ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
            ItemMeta meta = pane.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "No helmet!");
            pane.setItemMeta(meta);
            helm = pane;
        } else {
            helm = p.getInventory().getHelmet();
        }
        inv.setItem(slot, helm);
    }
    public static void addPlayerChestplate(Player p, Inventory inv, int slot){
        ItemStack chest;
        if(p.getInventory().getChestplate() == null || p.getInventory().getChestplate().getType() == Material.AIR){
            ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
            ItemMeta meta = pane.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "No chestplate!");
            pane.setItemMeta(meta);
            chest = pane;
        } else {
            chest = p.getInventory().getChestplate();
        }
        inv.setItem(slot, chest);
    }
    public static void addPlayerLeggings(Player p, Inventory inv, int slot){
        ItemStack legs;
        if(p.getInventory().getLeggings() == null || p.getInventory().getLeggings().getType() == Material.AIR){
            ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
            ItemMeta meta = pane.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "No leggings!");
            pane.setItemMeta(meta);
            legs = pane;
        } else {
            legs = p.getInventory().getLeggings();
        }
        inv.setItem(slot, legs);
    }
    public static void addPlayerBoots(Player p, Inventory inv, int slot){
        ItemStack boots;
        if(p.getInventory().getBoots() == null || p.getInventory().getBoots().getType() == Material.AIR){
            ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
            ItemMeta meta = pane.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "No boots!");
            pane.setItemMeta(meta);
            boots = pane;
        } else {
            boots = p.getInventory().getBoots();
        }
        inv.setItem(slot, boots);
    }
    public static String isOP(Player p){
        if(p.isOp()){
            return "&aTRUE";
        } else {
            return "&cFALSE";
        }
    }
    public static void addStack(Material material, int stacksize, short data, String name, Inventory inventory, int slot, String... lore){
        ItemStack itemStack = new ItemStack(material, stacksize, data);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        List<String> newlore = new ArrayList<>();
        for(String str : lore){
            newlore.add(ChatColor.translateAlternateColorCodes('&', str));
        }
        meta.setLore(newlore);
        itemStack.setItemMeta(meta);
        inventory.setItem(slot, itemStack);
    }
}
