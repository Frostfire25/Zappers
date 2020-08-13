package co.antiqu.spawnerzappers.entity;

import co.antiqu.spawnerzappers.objects.ZapperLog;
import com.massivecraft.massivecore.collections.MassiveList;
import com.massivecraft.massivecore.collections.MassiveMap;
import com.massivecraft.massivecore.collections.MassiveSet;
import com.massivecraft.massivecore.store.Entity;
import lombok.Getter;

public class ZapperLogs extends Entity<ZapperLogs> {

    public static ZapperLogs instance = new ZapperLogs();

    @Getter
    public MassiveSet<ZapperLog> zapperlogs;
    @Getter
    public Long timeSavedLast;

    public static ZapperLogs get() {
        return instance;
    }

    @Override
    public ZapperLogs load(ZapperLogs that) {
        super.load((ZapperLogs) that);
        this.zapperlogs = that.zapperlogs;
        return this;
    }

    public ZapperLogs() {
        this.zapperlogs = new MassiveSet<>();
        this.timeSavedLast = System.currentTimeMillis();
    }

    public void addLog(ZapperLog zapperLog) {
        get().zapperlogs.add(zapperLog);
        if(System.currentTimeMillis() - get().timeSavedLast >= MConf.get().saveLogsMiliCooldown) {
            get().timeSavedLast = System.currentTimeMillis();
            get().changed();
        }
    }

}
