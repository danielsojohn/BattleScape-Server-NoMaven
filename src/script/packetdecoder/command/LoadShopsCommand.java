package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.item.Shop;
import com.palidino.osrs.model.player.Player;

public class LoadShopsCommand implements Command {
    @Override
    public boolean canUse(Player player) {
        return player.getRights() == Player.RIGHTS_ADMIN;
    }

    @Override
    public void execute(Player player, String message) {
        Shop.init();
    }
}
