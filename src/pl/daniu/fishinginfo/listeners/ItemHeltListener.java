package pl.daniu.fishinginfo.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import pl.daniu.fishinginfo.variables.StaticVariables;

public class ItemHeltListener implements Listener {

    @EventHandler
    public void ItemHeld(PlayerItemHeldEvent event){
        Player p = event.getPlayer();
        if(!p.getItemInHand().equals(Material.FISHING_ROD))
        {
         if(StaticVariables.shedulers.get(p.getUniqueId()) != null){
             StaticVariables.shedulers.get(p.getUniqueId()).cancel();
             StaticVariables.shedulers.remove(p.getUniqueId());
         }
        }
    }
}
