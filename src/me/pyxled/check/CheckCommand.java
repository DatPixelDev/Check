package me.pyxled.check;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Pyxled Development 2016 (c)
 * File created: 02/24/2016
 */
public class CheckCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
        if(c.getName().equalsIgnoreCase("check")){
            if(!(s instanceof Player)){
                send(s, MessageType.ERROR, "&cYou must be a player to use this!");
                return true;
            }
            Player p = (Player)s;
            if(args.length == 0){
                if(!p.hasPermission("regime.check")){
                    send(p, MessageType.NOPERMS, "&cNo Permission");
                    return true;
                }
                send(p, MessageType.ERROR, "&cPlease specify a player to check!");
            }
            if(args.length == 1){
                if(!p.hasPermission("regime.check")){
                    send(p, MessageType.NOPERMS, "&cNo Permission");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if(target == null){
                    send(p, MessageType.ERROR, "&c" + args[0] + " is offline or is invalid!");
                    return true;
                }
                Inventories.checkTarget(target, p);
                send(p, MessageType.INFO, "&7Now viewing &e" + target.getName() + "'s &7information.");
            }
        }
        return true;
    }
    public void send(CommandSender cs, MessageType type, String msg){
        switch (type){
            case ERROR:
                cs.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&l> " + msg));
                break;
            case NOPERMS:
                cs.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lX " + msg));
                break;
            case SUCCESS:
                cs.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l> " + msg));
                break;
            case INFO:
                cs.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l> " + msg));
                break;
            default:
                break;
        }
    }
}
