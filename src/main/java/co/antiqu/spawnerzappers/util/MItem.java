package co.antiqu.spawnerzappers.util;

import com.massivecraft.massivecore.util.Txt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class MItem {

    private String name;
    private List<String> lore;
    private Material material;
    private int data;
    private boolean glow;

    public ItemStack build() {
        return glow  ? new ItemBuilder(material).name(Txt.parse(name)).lore(Txt.parse(lore)).addGlow().durability(data).build() : new ItemBuilder(material).name(Txt.parse(name)).lore(Txt.parse(lore)).durability(data).build();
    }

}
