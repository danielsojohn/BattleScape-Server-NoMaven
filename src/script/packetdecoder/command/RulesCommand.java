
package script.packetdecoder.command;

import com.palidino.osrs.Main;
import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;

public class RulesCommand implements Command {

    @Override
    public String getExample() {
        return "- Opens the rules.";
    }

    @Override
    public void execute(Player player, String message) {
        player.getGameEncoder().sendOpenURL(Main.getSettings().getRulesUrl());
    }
}
