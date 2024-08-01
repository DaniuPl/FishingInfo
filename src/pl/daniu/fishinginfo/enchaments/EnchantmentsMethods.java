package pl.daniu.fishinginfo.enchaments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EnchantmentsMethods {

    public static boolean hasEnchantedItem(ItemStack i, Enchantment e,  int enchantmentLevel) {

        if (i.getEnchantments().containsKey(e)) {
            if (i.getEnchantmentLevel(e) == enchantmentLevel) {
                return true;
            }
        }
        return false;
    }
}
