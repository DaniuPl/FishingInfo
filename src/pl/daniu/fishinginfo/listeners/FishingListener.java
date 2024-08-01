package pl.daniu.fishinginfo.listeners;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.scheduler.BukkitRunnable;

import org.bukkit.scheduler.BukkitTask;
import pl.daniu.fishinginfo.Main;
import pl.daniu.fishinginfo.chat.ChatMethods;
import pl.daniu.fishinginfo.configuration.messageConfiguration;
import pl.daniu.fishinginfo.variables.StaticVariables;

import java.util.HashMap;
import java.util.UUID;

import static pl.daniu.fishinginfo.enchaments.EnchantmentsMethods.hasEnchantedItem;


public class FishingListener implements Listener {

    Main main;
    messageConfiguration messageConfig;
    public FishingListener(Main plugin, messageConfiguration messConfig){
        main = plugin;
        messageConfig = messConfig;
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        Player player = event.getPlayer();

        if(event.getState().equals(PlayerFishEvent.State.FISHING)){
            int _time = getFishingTime(player);

            if(StaticVariables.timers.get(player.getUniqueId()) != null){
                StaticVariables.timers.remove(player.getUniqueId());
            }
            StaticVariables.timers.put(player.getUniqueId(),_time );
            event.getHook().setBiteChance(1d);

            if(StaticVariables.shedulers.get(player.getUniqueId()) != null){

                StaticVariables.shedulers.get(player.getUniqueId()).cancel();
                StaticVariables.shedulers.remove(player.getUniqueId());
            }

            if( StaticVariables.shedulers.get(player.getUniqueId()) == null){

                StaticVariables.shedulers.put(player.getUniqueId(), new BukkitRunnable() {
                    @Override
                    public void run() {
                        if(StaticVariables.timers.get(player.getUniqueId())  != 0){

                            event.getHook().setCustomName(ChatMethods.fixColor(messageConfig.getCatchFishFor().replace("{TIME}", String.valueOf(StaticVariables.timers.get(player.getUniqueId()) ))));
                            event.getHook().setCustomNameVisible(true);
                            StaticVariables.timers.replace(player.getUniqueId(), StaticVariables.timers.get(player.getUniqueId()), StaticVariables.timers.get(player.getUniqueId()) -1);
                        }else{
                            event.getHook().setCustomName(ChatMethods.fixColor(messageConfig.getCatchFishing()));
                            event.getHook().setCustomNameVisible(true);
                            cancel();
                        }
                    }
                }.runTaskTimer(main,0,20L));
            }
        }

        if(event.getState().equals(PlayerFishEvent.State.FAILED_ATTEMPT) && StaticVariables.shedulers.get(player.getUniqueId()) != null){
            StaticVariables.shedulers.get(player.getUniqueId()).cancel();
            StaticVariables.shedulers.remove(player.getUniqueId());
            return;
        }

        if(event.isCancelled() && StaticVariables.shedulers.get(player.getUniqueId()) != null){
            StaticVariables.shedulers.get(player.getUniqueId()).cancel();
            StaticVariables.shedulers.remove(player.getUniqueId());
        }

    }

    int getFishingTime(Player player){
        int level = 0;
        for (int i = 1; i <= 5; i++) {
            if (hasEnchantedItem(player.getItemInHand(), Enchantment.LURE, i)) {
                level = i;
                break;
            }
        }

        switch (level) {
            case 1:
                return 25;
            case 2:
                return 20;
            case 3:
                return 15;
            case 4:
                return 10;
            case 5:
                return 5;
            default:
                return 30;
        }
    }
}
