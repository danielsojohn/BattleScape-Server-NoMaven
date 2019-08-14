package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.PCombat;
import com.palidino.osrs.model.player.Player;

public class SkullCommand implements Command {
    @Override
    public String getExample() {
        return "- Skulls you.";
    }

    @Override
    public void execute(Player player, String message) {
        player.getCombat().setPKSkullDelay(PCombat.SKULL_DELAY);
    }
}
