package co.antiqu.spawnerzappers.util;

import com.massivecraft.factions.*;
import com.massivecraft.factions.struct.Relation;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class FactionUtil
{
    public static Faction getUUIDFactionByPlayer(Player player) {
        return FPlayers.getInstance().getByPlayer(player).getFaction();
    }

    public static boolean can(boolean useMassive, Location location, Player player) {
            Faction faction = Board.getInstance().getFactionAt(new FLocation(location));
            Faction myFaction = getUUIDFactionByPlayer(player);
            if (faction == myFaction) {
                return !myFaction.isNone();
            }
            return false;
    }
}
