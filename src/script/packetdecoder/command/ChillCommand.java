package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;

public class ChillCommand implements Command {
    @Override
    public boolean canUse(Player player) {
        return !player.getController().inWilderness() && !player.getController().inPvPWorld();
    }

    @Override
    public void execute(Player player, String message) {
        player.getMagic().standardTeleport(3220, 3395, 0);
        player.getGameEncoder().sendMessage("You teleport to Chilling area..");
    }
}
