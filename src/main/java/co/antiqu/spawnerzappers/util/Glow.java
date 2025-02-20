package co.antiqu.spawnerzappers.util;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class Glow extends Enchantment {

    public Glow(int id) {
        super(id);
    }

    public boolean canEnchantItem(ItemStack arg0) {
        return true;
    }

    public boolean conflictsWith(Enchantment arg0) {
        return false;
    }

    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ALL;
    }

    public int getMaxLevel() {
        return 1;
    }

    public String getName() {
        return "Glow";
    }

    public int getStartLevel() {
        return 1;
    }

}
