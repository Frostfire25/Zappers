package co.antiqu.spawnerzappers;

import com.massivecraft.factions.struct.Permission;
import com.massivecraft.massivecore.Identified;
import com.massivecraft.massivecore.MassiveCorePerm;
import com.massivecraft.massivecore.util.PermissionUtil;
import org.bukkit.permissions.Permissible;

public enum  Perm implements Identified {
    BASECOMMAND,
    GIVE,
    INSPECT,
    ADMIN;

    private final String id;

    private Perm() {
        id = PermissionUtil.createPermissionId(SpawnerZappers.getInstance(),this);
    }

    @Override
    public String getId() {
        return id;
    }

    public boolean has(Permissible permissible, final boolean verboose) {
        return PermissionUtil.hasPermission(permissible, this, verboose);
    }

    public boolean has(Permissible permissible) {
        return PermissionUtil.hasPermission(permissible, this);
    }
}
