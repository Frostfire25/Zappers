package co.antiqu.spawnerzappers.engine;

import co.antiqu.spawnerzappers.SpawnerZappers;
import co.antiqu.spawnerzappers.entity.MConf;
import co.antiqu.spawnerzappers.entity.ZapperLogs;
import co.antiqu.spawnerzappers.objects.ZapperLog;
import co.antiqu.spawnerzappers.util.Color;
import co.antiqu.spawnerzappers.util.Cuboid;
import co.antiqu.spawnerzappers.util.TimeUtil;
import co.antiqu.spawnerzappers.util.Util;
import com.massivecraft.factions.Board;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.struct.Relation;
import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.mixin.MixinMessage;
import de.dustplanet.silkspawners.SilkSpawners;
import de.dustplanet.util.SilkUtil;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jdgames.mobextras.entity.SpawnerStacks;
import org.jdgames.mobextras.entity.object.SpawnerStack;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;

public class EngineZapper extends Engine {

    public static EngineZapper i = new EngineZapper();

    public static EngineZapper get() {
        return i;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent evt) {
        if(Util.areTheSameItems(evt.getItemInHand(),MConf.get().zapper.build()))
            evt.setCancelled(true);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent evt) {
        if(evt.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = evt.getPlayer();

            if(Util.areTheSameItems(player.getInventory().getItemInHand(), MConf.get().zapper.build())) {
                FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);

                if(evt.getClickedBlock() == null || evt.getClickedBlock().getType() != Material.MOB_SPAWNER)
                    return;
                 //if(Board.getInstance().getFactionAt(fPlayer.getLastStoodAt()).getRelationTo(fPlayer.getFaction()) == Relation.NEUTRAL) {

                    if(!MConf.get().isCanBeUsed()) {
                        player.sendMessage(MConf.get().NOT_USABLE);
                        return;
                    }
                    Location loc = evt.getClickedBlock().getLocation();
                    int dis = MConf.get().zapping_distance/2;
                    Cuboid cuboid = new Cuboid(new Location(loc.getWorld(),loc.getBlockX()-dis,loc.getBlockY()-dis,loc.getBlockZ()-dis),
                    new Location(loc.getWorld(),loc.getBlockX()+dis,loc.getBlockY()+dis,loc.getBlockZ()+dis));

                    //System.out.println(loc.getBlock().getType().name());
                    // System.out.println(loc.getWorld().getName() + "  " + (loc.getBlockX()-dis) + " " + (loc.getBlockY()-dis) + " " + (loc.getBlockZ()-dis));
                    // System.out.println(loc.getWorld().getName() + "  " + (loc.getBlockX()+dis) + " " + (loc.getBlockY()+dis) + " " + (loc.getBlockZ()+dis));
                    //System.out.println(MConf.get().isMobExtras());
                    HashMap<String,Integer> spawnerLogs = new HashMap<>();
                    HashMap<Block,ItemStack> spawnersToDrop = new HashMap();
                    Iterator<Block> blocks = cuboid.iterator();
                    while(blocks.hasNext()) {
                        Block block = blocks.next();
                        //System.out.println(block.getType().name());
                        if(block.getType() == Material.MOB_SPAWNER) {
                            if (!MConf.get().isMobExtras()) {
                                //System.out.println("here 2");
                                //spawnParticles(evt.getClickedBlock().getLocation(),block.getLocation());
                                //System.out.println(SpawnerZappers.getInstance().getSilkUtil().getSpawnerEntityID(block));
                                String spawnerName = SpawnerZappers.getInstance().getSilkUtil().getSpawnerEntityID(block);
                                spawnersToDrop.put(block, SpawnerZappers.getInstance().getSilkUtil().newSpawnerItem(spawnerName, Color.t("&e"+spawnerName + " &fSpawner"), 1, false));
                                if(spawnerLogs.keySet().contains(spawnerName)) {
                                    spawnerLogs.put(spawnerName,spawnerLogs.get(spawnerName)+1);
                                } else {
                                    spawnerLogs.put(spawnerName,1);
                                }
                            } else {

                                SpawnerStack spawnerStack = SpawnerStacks.get().getSpawnerStackAtLocation(block.getLocation());
                                if(spawnerStack == null)
                                    return;
                                String spawnerName = SpawnerZappers.getInstance().getSilkUtil().getSpawnerEntityID(block);
                                for(int i = 0; i < spawnerStack.getStackSize(); i++) {
                                    spawnersToDrop.put(block, SpawnerZappers.getInstance().getSilkUtil().newSpawnerItem(spawnerName, Color.t("&e"+spawnerName + " &fSpawner"), 1, false));
                                    if(spawnerLogs.keySet().contains(spawnerName)) {
                                        spawnerLogs.put(spawnerName,spawnerLogs.get(spawnerName)+1);
                                    } else {
                                        spawnerLogs.put(spawnerName,1);
                                    }
                                }

                            }
                        }
                    }


                    if(spawnersToDrop.keySet().isEmpty())
                        return;

                    spawnersToDrop.keySet().forEach(n -> {
                        n.setType(Material.AIR);
                        ItemStack spawner = spawnersToDrop.get(n);
                        loc.getWorld().dropItem(n.getLocation(),spawner);
                    });

                    Util.removeItemInHandFromPlayer(player);

                    String zappedSpawnersMessage = "";
                    for(String n : spawnerLogs.keySet()) {
                        zappedSpawnersMessage += "" + spawnerLogs.get(n) + "x " + n + "'s ";
                    }

                    MixinMessage.get().messageOne(player,MConf.get().ZAPPED.replaceAll("%s", ""+zappedSpawnersMessage));

                    ZapperLogs.get().addLog(new ZapperLog(player.getUniqueId().toString(),player.getName(),
                            System.currentTimeMillis(), TimeUtil.formatTime(System.currentTimeMillis()), spawnerLogs,
                            new int[]{loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()}, loc.getWorld().getName()));
                //} else {
                //     MixinMessage.get().messageOne(player,MConf.get().ENEMY_TERRITORY);
                 //}
           }
        }
    }

    /*
    private void spawnParticles(Location loc, Location loc2) {
        if(loc2.distance(loc2) <= 0)
            return;
        Location particle = new Location(loc.getWorld(),loc.getBlockX(),loc.getBlockY(),loc.getBlockZ());
        Vector increase = new Vector(loc.getBlockX()/10,loc.getBlockY()/10,loc.getBlockZ()/10);
        for(int i = 0; i < loc.toVector().distance(loc2.toVector()) * 10; i++) {
            particle.add(increase);
            particle.getWorld().playEffect(particle, Effect.COLOURED_DUST, ThreadLocalRandom.current().nextInt(1,10));
        }
        /*
        double amount = loc.toVector().distance(loc2.toVector());
        System.out.println(amount);
        new BukkitRunnable() {
            @Override
            public void run() {

            }
        }.runTaskTimerAsynchronously(SpawnerZappers.getInstance(), 0, 5);
    }*/

}
