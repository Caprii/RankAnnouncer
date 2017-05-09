package com.capri.Core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;

public class Main extends JavaPlugin{
    int minutes = getConfig().getInt("interval in minutes");
    final int ticks = 1200 * minutes;
    public void onEnable(){
        getConfig().options().copyDefaults(true);
        saveConfig();
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "RankAnnounce" + ChatColor.AQUA + "(1.0)");
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("rank to be announced"))
                + rankAnnounce().toString());
            }
        }, 1, ticks);
    }
    public void onDisable(){
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "RankAnnounce Disabled!");
    }
    public StringBuilder rankAnnounce(){
        ArrayList<String> oPlayer = new ArrayList<>();
        for(Player online : Bukkit.getServer().getOnlinePlayers()) {
            if(online.hasPermission("rannouncer.announce")){
                oPlayer.add(online.getPlayer().getName());
                if(online.isOp()){
                    oPlayer.remove(online);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(String s : oPlayer){
            sb.append(s + ", ");
        }
        return sb;
    }
}
