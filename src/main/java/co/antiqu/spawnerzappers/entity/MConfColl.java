package co.antiqu.spawnerzappers.entity;

import com.massivecraft.massivecore.MassiveCore;
import com.massivecraft.massivecore.store.Coll;

public class MConfColl extends Coll<MConf> {

    public static MConfColl instance = new MConfColl();

    public void onTick() {
        super.onTick();
    }

    public void setActive(boolean active) {
        super.setActive(active);
        if (!active) {
            return;
        }
        MConf.instance = this.get(MassiveCore.INSTANCE, true);
    }

    public static MConfColl get() {
        return instance;
    }

}
