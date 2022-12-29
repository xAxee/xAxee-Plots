package pl.xaxee.plots.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ChatUtil {
    public static String color(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
    public static void sendMessage(Player player, String msg){
        player.sendMessage(color(msg));
    }
    public static void sendMessage(CommandSender sender, String msg){
        sender.sendMessage(color(msg));
    }
    public static void sendMessage(Player player, List<String> msg){ for(String m : msg){ player.sendMessage(color(m)); } }
    public static void sendMessage(CommandSender sender, List<String> msg){ for(String m : msg){ sender.sendMessage(color(m)); } }
    public static void sendMessage(CommandSender sender, List<String> msg, HashMap<String, String> map){
        for(String m : msg) {
            String fMsg = m;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                fMsg = fMsg.replace(entry.getKey(), entry.getValue());
            }
            sender.sendMessage(color(fMsg));
        }
    }
    public static void sendActionBar(Player player, String msg) {
        player.sendActionBar(color(msg));
    }
}
