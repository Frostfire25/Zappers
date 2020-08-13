package co.antiqu.spawnerzappers.integration;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;
import de.dustplanet.util.SilkUtil;
import org.bukkit.event.EventHandler;

public class ISilkUtil extends Engine {

    public static ISilkUtil i = new ISilkUtil();

    public static ISilkUtil get() {
        return i;
    }

}
