package co.antiqu.spawnerzappers.entity;

import com.massivecraft.massivecore.MassiveCore;
import com.massivecraft.massivecore.store.Coll;

public class ZapperLogsColl extends Coll<ZapperLogs> {

    public static ZapperLogsColl instance = new ZapperLogsColl();

    public void onTick() {
        super.onTick();
    }

    public void setActive(boolean active) {
        super.setActive(active);
        if (!active) {
            return;
        }
        ZapperLogs.instance = this.get(MassiveCore.INSTANCE,true);
    }

    public static ZapperLogsColl get() {
        return instance;
    }

}
