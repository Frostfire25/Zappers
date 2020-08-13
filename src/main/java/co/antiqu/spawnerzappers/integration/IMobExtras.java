package co.antiqu.spawnerzappers.integration;

import co.antiqu.spawnerzappers.SpawnerZappers;
import co.antiqu.spawnerzappers.entity.MConf;
import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;
import org.bukkit.Bukkit;

public class IMobExtras extends Integration {

    public static IMobExtras instance = new IMobExtras();

    public static IMobExtras get() {
        return instance;
    }

    public IMobExtras() {
        this.setName("MobExtras");
        if(MConf.get().isMobExtras)
            checkEnabled();
    }

    public void checkEnabled() {
        if(Bukkit.getPluginManager().getPlugin("MobExtras") == null || !(Bukkit.getPluginManager().getPlugin("MobExtras") == null)) {
            Bukkit.getPluginManager().disablePlugin(SpawnerZappers.getInstance());
        }
    }
}
