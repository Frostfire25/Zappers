package co.antiqu.spawnerzappers.cmd;

import co.antiqu.spawnerzappers.Perm;
import co.antiqu.spawnerzappers.entity.MConf;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.primitive.TypeInteger;
import com.massivecraft.massivecore.command.type.sender.TypePlayer;
import com.massivecraft.massivecore.mixin.MixinMessage;
import org.bukkit.entity.Player;

public class CmdZapperGive extends AlexCommand {

    public static CmdZapperGive i = new CmdZapperGive();

    public static CmdZapperGive get() {
        return i;
    }

    public CmdZapperGive() {
        addAliases("give");
        addParameter(TypePlayer.get());
        addParameter(TypeInteger.get());
        addRequirements(RequirementHasPerm.get(Perm.ADMIN));
    }

    public void perform() throws MassiveException {
        Player player = readArg();
        int amount = readArg();
        if(amount <= 0) {
            sender.sendMessage(MConf.get().GREATER_THAN_ONE);
            return;
        }
        for(int i = 0; i < amount; i++) {
            player.getInventory().addItem(MConf.get().zapper.build());
        }
        MixinMessage.get().messageOne(player,MConf.get().ZAPPER_GIVEN.replaceAll("%s", ""+amount));
    }

}
