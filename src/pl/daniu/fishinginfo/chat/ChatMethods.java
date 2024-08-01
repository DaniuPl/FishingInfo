package pl.daniu.fishinginfo.chat;

import net.md_5.bungee.api.ChatColor;

public class ChatMethods {
    public static String fixColor(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
