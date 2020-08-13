package co.antiqu.spawnerzappers.entity;

import co.antiqu.spawnerzappers.util.Color;
import co.antiqu.spawnerzappers.util.MItem;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.store.EntityInternal;
import com.massivecraft.massivecore.util.MUtil;
import lombok.Getter;
import org.bukkit.Material;

public class MConf extends Entity<MConf> {

    public static MConf instance = new MConf();

    public static MConf get() {
        return instance;
    }

    @Override
    public MConf load(MConf that) {
        super.load((MConf) that);
        return this;
    }

    public MItem zapper;

    public String ZAPPER_GIVEN;
    public String GREATER_THAN_ONE;
    public String NOT_USABLE;
    public String ZAPPED;
    public String ENEMY_TERRITORY;

    @Getter
    public boolean isMobExtras;

    @Getter
    public boolean canBeUsed;

    public int zapping_distance;

    public long saveLogsMiliCooldown;

    public MConf() {
        this.saveLogsMiliCooldown = 1000L;
        this.isMobExtras = false;
        this.canBeUsed = true;
        this.zapping_distance = 10;
        this.ZAPPER_GIVEN = Color.t("&aYou have been given %s Zapper");
        this.GREATER_THAN_ONE = Color.t("&cAmount must be > 1");
        this.NOT_USABLE = Color.t("&cZappers can not be used until Grace is over.");
        this.ENEMY_TERRITORY = Color.t("&cZappers can only be used in Enemy territory");
        this.ZAPPED = Color.t("&eYou have zapped %s.");
        this.zapper = new MItem("&c&lZapper", MUtil.list("&f","&fCan be used In: &cEnemy Territory", "&fRadius: &c10x10","&f","&7&o(( Right-Click A spawner To Zap Nearby Spawners )) "), Material.NETHER_STAR, 0 , true);
    }

}
