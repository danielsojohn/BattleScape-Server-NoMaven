package script.packetdecoder.command;

import com.palidino.osrs.Main;
import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;

public class StoreCommand implements Command {

    @Override
    public String getExample() {
        return "- Opens up the store page.";
    }

    @Override
    public void execute(Player player, String message) {
        player.getGameEncoder().sendOpenURL(Main.getSettings().getStoreUrl());
    }
}
