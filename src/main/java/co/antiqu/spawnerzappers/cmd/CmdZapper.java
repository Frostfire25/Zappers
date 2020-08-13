package co.antiqu.spawnerzappers.cmd;

import java.util.Arrays;
import java.util.List;

public class CmdZapper extends AlexCommand {

    public static CmdZapper i = new CmdZapper();

    public static CmdZapper get() {
        return i;
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("zapper", "zap");
    }

    public CmdZapperGive cmdZapper = new CmdZapperGive();
    public CmdZapperInspect cmdZapperInspect = new CmdZapperInspect();

}
