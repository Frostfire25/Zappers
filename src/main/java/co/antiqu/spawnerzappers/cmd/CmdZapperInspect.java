package co.antiqu.spawnerzappers.cmd;

import co.antiqu.spawnerzappers.Perm;
import co.antiqu.spawnerzappers.entity.ZapperLogs;
import co.antiqu.spawnerzappers.objects.ZapperLog;
import co.antiqu.spawnerzappers.util.Color;
import co.antiqu.spawnerzappers.util.TimeUtil;
import co.antiqu.spawnerzappers.util.Util;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.mixin.MixinMessage;
import com.massivecraft.massivecore.util.MUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Set;

public class CmdZapperInspect extends AlexCommand {

    public static CmdZapperInspect i = new CmdZapperInspect();

    public static CmdZapperInspect get() {
        return i;
    }

    public CmdZapperInspect() {
        addAliases("inspect");
        addAliases("i");
        addRequirements(RequirementHasPerm.get(Perm.INSPECT));
        addRequirements(RequirementIsPlayer.get());
    }

    public void perform() throws MassiveException {
        Player player = (Player) sender;
        Location loc = player.getTargetBlock((Set<Material>) null, 5).getLocation();
        for(ZapperLog n : ZapperLogs.get().getZapperlogs()) {
            Location loc2 = new Location(Bukkit.getWorld(n.getWorld()),n.getLocation()[0],n.getLocation()[1], n.getLocation()[2]);
            if(Util.areSameLocations(loc,loc2)) {

                String zappedSpawnersMessage = "";
                for(String m : n.getSpawnersRemoved().keySet()) {
                    zappedSpawnersMessage += "" + n.getSpawnersRemoved().get(m) + "x " + m + "'s ";
                }

                MixinMessage.get().messageOne(player,MUtil.list(
                        Color.t("&f-------------"),
                        Color.t("&cLocation: &f"+n.getLocation()[0] + " " +n.getLocation()[1] + " "+n.getLocation()[2] + ""),
                        Color.t("&cPlayer: &f"+n.getPlayer() + ""),
                        Color.t("&cTime: &f"+ TimeUtil.formatPlayTime((System.currentTimeMillis() - n.getTimeMilis())) + ""),
                        Color.t("&cSpawners Zapped: &f"+zappedSpawnersMessage+""),
                        Color.t("&f-------------")
                ));
                return;
            }
        }
        MixinMessage.get().messageOne(player, Color.t("&cNo zappers were used here"));
    }
}
