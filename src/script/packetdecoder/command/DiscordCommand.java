package script.packetdecoder.command;

import com.palidino.osrs.Main;
import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;

public class DiscordCommand implements Command {
    @Override
    public String getExample() {
        return "- Connects to the official Battle-Scape discord server.";
    }

    @Override
    public void execute(Player player, String message) {
        player.getGameEncoder().sendOpenURL(Main.getSettings().getDiscordUrl());
    }
}
