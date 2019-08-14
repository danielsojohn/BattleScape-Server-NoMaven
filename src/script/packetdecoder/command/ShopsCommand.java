package script.packetdecoder.command;

import com.palidino.osrs.Main;
import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;

public class ShopsCommand implements Command {
    @Override
    public boolean canUse(Player player) {
        return Main.isSpawn() && !player.getController().inWilderness() && !player.getController().inPvPWorld()
                && player.getController().canTeleport(false);
    }

    @Override
    public void execute(Player player, String message) {
        player.getMovement().teleport(3096, 3503);
    }
}
