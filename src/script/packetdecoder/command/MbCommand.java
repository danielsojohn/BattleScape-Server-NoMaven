package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;

public class MbCommand implements Command {
    @Override
    public boolean canUse(Player player) {
        return !player.getController().inWilderness() && !player.getController().inPvPWorld();
    }

    @Override
    public void execute(Player player, String message) {
        if (player.getController().canTeleport(true)) {
            player.getMagic().standardTeleport(2539, 4718, 0);
            player.getGameEncoder().sendMessage("You teleport to Mage bank..");
        } else {
            return;
        }
    }
}
