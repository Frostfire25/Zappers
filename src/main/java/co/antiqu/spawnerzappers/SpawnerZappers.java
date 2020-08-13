package co.antiqu.spawnerzappers;

import com.massivecraft.massivecore.MassivePlugin;
import de.dustplanet.util.SilkUtil;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class SpawnerZappers extends MassivePlugin {

    @Getter
    private static SpawnerZappers instance;

    @Getter
    private SilkUtil silkUtil;

    public SpawnerZappers() {
        SpawnerZappers.instance = this;
    }

    @Override
    public void onEnableInner() {
        activateAuto();
        new BukkitRunnable() {
            @Override
            public void run() {
               silkUtil = SilkUtil.hookIntoSilkSpanwers();
            }
        }.runTaskLater(instance,40);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean isVersionSynchronized() {
        return false;
    }
}
