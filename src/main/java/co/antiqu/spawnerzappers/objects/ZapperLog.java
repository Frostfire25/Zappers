package co.antiqu.spawnerzappers.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.World;

import java.util.Map;

@AllArgsConstructor
public class ZapperLog {

    @Getter
    private String UUID;
    @Getter
    private String player;
    @Getter
    private Long timeMilis;
    @Getter
    private String timeFormattedEst;
    @Getter
    private Map<String,Integer> spawnersRemoved;
    @Getter
    private int[] location;
    @Getter
    private String world;

}
